const getLinkAPI = async (url) => {
  let response = await axios
    .get(`https://wwwsummonmusicapi.herokuapp.com/get?q=${url}`)
    .catch(function (error) {
      console.log(error);
    });

  const html = new DOMParser().parseFromString(response.data, 'text/html');

  let name = html.querySelector('.bh-info').children[0].outerText;
  let audio = html.querySelector('#audio-player-container').dataset.src;

  return {
    name: name,
    audio: audio,
  };
};

function getCookie(cName) {
  const name = cName + '=';
  const cDecoded = decodeURIComponent(document.cookie); //to be careful
  const cArr = cDecoded.split('; ');
  let res;
  cArr.forEach((val) => {
    if (val.indexOf(name) === 0) res = val.substring(name.length);
  });
  return res;
}
