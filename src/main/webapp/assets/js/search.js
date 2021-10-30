const { useEffect, useState } = React;

var timeOut = null;
const Search = () => {
  let [value, setValue] = useState("");
  let [data, setData] = useState([]);

  useEffect(() => {
    setData([]);
    if (typeof timeOut !== "undefined") {
      clearTimeout(timeOut);
    }

    if (typeof value === "string" && value.trim()) {
      timeOut = setTimeout(() => {
        console.log(value);
        searchAPI(value).then((data) => {
          setData(data);
        });
      }, 300);
    }
  }, [value]);

  const handleClick = (link) => {
    getLinkAPI(link).then(({ audio, name }) => {
      audioEl.pause();
      timeRender.style.width = `4.5%`;
      play.classList.remove("ri-pause-fill");
      play.classList.add("ri-play-fill");
      audioEl.currentTime = 0;
      audioEl.src = audio;
      playNameEl.innerText = name;
      download.href = audio;

      setValue("");
      setData([]);
    });
  };

  const handleSearch = () => {
    return data && data.length > 0 ? (
      <ul className="search__list">
        {data.map((value) => (
          <li
            onClick={() => handleClick(value.link)}
            key={value.id}
            className="search__item"
          >
            {value.name}
          </li>
        ))}
      </ul>
    ) : (
      <div
        style={{ backgroundColor: "#3e1981" }}
        className="load w-full h-full flex items-center justify-center p-6 rounded-md"
      >
        <div className=" loader ease-linear rounded-full border-4 border-t-4 border-gray-200 h-12 w-12 mb-4"></div>
      </div>
    );
  };

  return (
    <>
      <i className="ml-4 text-white text-2xl ri-search-line"></i>
      <input
        className="w-full h-full bg-transparent"
        type="text"
        placeholder="Tìm kiếm bài hát...."
        value={value}
        onChange={(e) => setValue(e.target.value)}
      />
      <div className="box__search">
        {typeof value === "string" && value.trim() && handleSearch()}
      </div>
    </>
  );
};

ReactDOM.render(<Search />, document.getElementById("search"));

const apiUrl = (tag) =>
  `https://wwwsummonmusicapi.herokuapp.com/search?q=${tag}`;

const searchAPI = async (name) => {
  let response = await axios.get(apiUrl(name)).catch(function (error) {
    console.log(error);
  });

  const html = new DOMParser().parseFromString(response.data, "text/html");

  let list = Array.from(html.querySelectorAll(".menu")).map((value) => {
    return value.querySelector(".detail-info");
  });

  return list.map((value, index) => ({
    id: index,
    link: value.querySelector("a").href,
    name: value.querySelector("a").outerText,
  }));
};
