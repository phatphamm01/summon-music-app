const $ = (name) => document.getElementById(name);

const renderNumber = (number) =>
  ('' + number).length === 1 ? `0${number}` : number;

class Audio {
  constructor() {
    this.audioEl = $('audio');
    this.playEl = $('play');
    this.playNameEl = $('play-name');
    this.setTimeEl = $('set-time');
    this.downloadEl = $('download');
    this.timeRenderEl = $('time-render');
    this.timeCurrentEl = $('time-current');
    this.timeDurationEl = $('time-duration');
  }

  play() {
    this.audioEl.play();
  }

  pause() {
    this.audioEl.pause();
  }

  timeCurrent() {
    return this.audioEl.currentTime;
  }

  timeDuration() {
    return this.audioEl.duration;
  }

  handlePlay() {
    this.playEl.classList.remove('ri-play-fill');
    this.playEl.classList.add('ri-pause-fill');
    console.log('play');
  }

  handlePause() {
    this.playEl.classList.remove('ri-pause-fill');
    this.playEl.classList.add('ri-play-fill');
    console.log('pause');
  }

  handleTime(currentTime) {
    let mins = Math.floor(currentTime / 60);
    let seconds = Math.round(currentTime) - mins * 60;

    return { mins, seconds };
  }

  setSlidePlay(currentTime, duration) {
    let parcent = (currentTime / duration) * 100;
    this.timeRenderEl.style.width = `${parcent + 4.5}%`;
  }

  setTimeDuration(time) {
    this.timeDurationEl.innerText = time;
  }

  setAudio(audio, name) {
    this.audioEl.pause();
    this.timeRenderEl.style.width = `4.5%`;
    this.playEl.classList.remove('ri-pause-fill');
    this.playEl.classList.add('ri-play-fill');
    this.audioEl.currentTime = 0;
    this.audioEl.src = audio;
    this.downloadEl.href = audio;
    this.playNameEl.innerText = name;
  }

  addEvent() {
    this.audioEl.addEventListener('play', this.handlePlay.bind(this), false);

    this.audioEl.addEventListener('pause', this.handlePause.bind(this), false);

    this.audioEl.addEventListener('timeupdate', (ev) => {
      let duration = this.timeDuration();
      let currentTime = this.timeCurrent();

      this.setSlidePlay(currentTime, duration);

      let time = this.handleTime(duration);
      this.setTimeDuration(`${time.mins}:${renderNumber(time.seconds)}`);
    });

    var playMusic = function () {
      return !Main.audioEl.paused ? Main.audioEl.pause() : Main.audioEl.play();
    };

    this.audioEl.addEventListener('loadstart', (ev) => {
      console.log('can play');
      this.setTimeDuration('00:00');

      this.playEl.addEventListener('click', playMusic, true);
    });

    this.audioEl.addEventListener('emptied', (ev) => {
      console.log('cancel play');

      this.playEl.removeEventListener('click', playMusic, true);
    });

    this.audioEl.addEventListener('loadedmetadata', (ev) => {});

    this.setTimeEl.addEventListener('click', (ev) => {
      this.play();

      let parcent = ev.offsetX / this.setTimeEl.offsetWidth;
      let duration = this.audioEl.duration;

      if (this.audioEl && this.audioEl.currentTime) {
        this.audioEl.currentTime = parcent * duration;
      }
    });
  }
}

var Main = new Audio();
Main.addEvent();
