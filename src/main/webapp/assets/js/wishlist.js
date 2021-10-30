const { useEffect, useState } = React;

let idUser = $("idUser");

const Top = () => {
  let [data, setData] = useState([]);

  useEffect(() => {
    getTopAPI().then((data) => {
      setData(data);
      console.log(data);
    });
  }, []);

  const handleClick = (link) => {
    getLinkAPI(link).then(({ audio, name }) => {
      audioEl.pause();
      timeRender.style.width = `4.5%`;
      play.classList.remove("ri-pause-fill");
      play.classList.add("ri-play-fill");
      audioEl.currentTime = 0;
      audioEl.src = audio;
      download.href = audio;
      playNameEl.innerText = name;
    });
  };

  const handleLike = async (item) => {
    let newData = [...data];

    let index = newData.findIndex((value) => value.link === item.link);

    if (index >= 0) {
      newData[index].like = !newData[index].like;
    }
    setData(newData);

    if (idUser && idUser.value) {
      let options = {
        duration: 3000,
      };

      try {
        await axios.post(
          "./wish-list",
          { id: idUser.value, name: item.name, img: item.img, link: item.link },
          {
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
          }
        );

        options.text = "Update successful !!!";
        Toastify(options).showToast();
      } catch (err) {
        options.text = "Update faild !!!";
        options.style = { background: "red" };
        Toastify(options).showToast();
      }
    }
  };

  const getTopAPI = async () => {
    if (idUser && idUser.value) {
      let result = await axios.get(
        "./wish-list",
        { params: { id: idUser.value } },
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
        }
      );
      return (
        result &&
        result.data &&
        result.data.map((value, index) => {
          return {
            id: index,
            img: value.img,
            like: true,
            link: value.link,
            name: value.name,
          };
        })
      );
    }
  };

  return data && data.length > 0 ? (
    <ul className="album__list grid grid-cols-2 gap-x-6">
      {data.map((value) => (
        <li key={value.id} className="album__item text-center">
          <div className="img p-2">
            {idUser && idUser.value && (
              <i
                onClick={() => handleLike(value)}
                className={`ri-heart-fill ${value.like && "like"}`}
              ></i>
            )}

            <img
              onClick={() => handleClick(value.link)}
              src={value.img}
              alt="ảnh"
            />
          </div>
          <span className="name">{value.name}</span>
        </li>
      ))}
    </ul>
  ) : (
    <div className="load w-full h-full flex items-center justify-center">
      <div className=" loader ease-linear rounded-full border-4 border-t-4 border-gray-200 h-12 w-12 mb-4"></div>
    </div>
  );
};

ReactDOM.render(<Top />, document.getElementById("top"));
