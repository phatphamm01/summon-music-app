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
      playNameEl.innerText = name;
    });
  };

  const handleLike = async (item) => {
    if (idUser && idUser.value) {
      const res = await axios.post(
        "./wish-list",
        { id: idUser.value, name: item.name, img: item.img, link: item.link },
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res.data);
    }
  };

  return data && data.length > 0 ? (
    <ul className="album__list grid grid-cols-2 gap-x-6">
      {data.map((value) => (
        <li
          onClick={() => handleClick(value.link)}
          key={value.id}
          className="album__item text-center"
        >
          <div className="img p-2">
            {idUser && idUser.value && (
              <i
                onClick={() => handleLike(value)}
                className="ri-heart-fill like"
              ></i>
            )}

            <img src={value.img} alt="ảnh" />
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
          link: value.link,
          name: value.name,
        };
      })
    );
  }
};
