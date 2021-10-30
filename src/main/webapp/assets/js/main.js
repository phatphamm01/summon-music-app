const $ = (name) => document.getElementById(name);

let audio = $("audio");
let timeDuration = $("time-duration");
let timeCurrent = $("time-current");
let timeRender = $("time-render");
let download = $("download");

let setTime = $("set-time");

let play = $("play");

const renderNumber = (number) => {
  if (("" + number).length === 1) {
    return `0${number}`;
  }
  return number;
};

audio.addEventListener("play", () => {
  play.classList.remove("ri-play-fill");
  play.classList.add("ri-pause-fill");
  console.log("play");
});

audio.addEventListener("pause", () => {
  play.classList.remove("ri-pause-fill");
  play.classList.add("ri-play-fill");
  console.log("pause");
});

audio.addEventListener("loadeddata", () => {
  console.log("done!!!");
  let duration = audio.duration;

  let mins = Math.floor(duration / 60);
  let seconds = Math.round(duration) - mins * 60;

  timeDuration.innerText = `${mins}:${renderNumber(seconds)}`;
});

audio.addEventListener("timeupdate", (ev) => {
  let duration = audio.duration;
  _duration = duration;
  let currentTime = audio.currentTime;
  let parcent = (currentTime / duration) * 100;
  timeRender.style.width = `${parcent + 4.5}%`;

  let mins = Math.floor(currentTime / 60);
  let seconds = Math.round(currentTime) - mins * 60;
  timeCurrent.innerText = `${mins}:${renderNumber(seconds)}`;
});

setTime.addEventListener("click", (ev) => {
  audio.play();
  let parcent = ev.offsetX / setTime.offsetWidth;
  let duration = audio.duration;
  if (audio && audio.currentTime) {
    audio.currentTime = parcent * duration;
  }
});

function playMusic() {
  if (!audio.paused) {
    audio.pause();
  } else {
    audio.play();
  }
}

audio.addEventListener("emptied", (ev) => {
  console.log("cancel play");
  play.removeEventListener("click", playMusic, true);
});

audio.addEventListener("loadstart", (ev) => {
  console.log("can play");
  play.addEventListener("click", playMusic, true);
});
