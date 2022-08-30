<template>
  <!-- 音乐播放器 -->
  <div class="container">
    <h2>{{ musicName }}</h2>
    <audio
      ref="Ref_audioPlayer"
      controls
      :src="audioPathDic[musicName]"
      @ended="overAudio"
      @pause="onPause"
      @play="onPlay"
      @loadeddata="loadeddata"
      @timeupdate="timeupdate"
    ></audio>
    <div>
      <el-tag>{{ msg }}</el-tag>
    </div>

    <div class="listBox">
      <h3 class="subTitle">CONTROL PANEL</h3>

      <div class="myPlayer">
        <el-slider
          @mousedown="isDraging = 'true'"
          @mouseup="isDraging = 'false'"
          @change="currentRateChange"
          v-model="currentRate"
          :marks="marks"
          :show-tooltip="false"
        >
        </el-slider>
        <div class="controlBox">
          <i @click="move(-5)" class="el-icon-d-arrow-left"></i>
          <i
            type="primary"
            v-if="playstatus"
            @click="pause"
            class="el-icon-video-pause"
          ></i>
          <i type="primary" v-else @click="play" class="el-icon-video-play"></i>
          <i @click="replay" class="el-icon-refresh-left"></i>
          <i @click="move(5)" class="el-icon-d-arrow-right"></i>
        </div>
      </div>
      <h3 class="subTitle">PLAY LIST</h3>
      <ul>
        <li
          @click="changeMusice(key)"
          class="musicItem"
          v-for="(value, key) in audioPathDic"
          :key="key"
        >
          {{ key }}
        </li>
      </ul>
      <h3 class="subTitle">GETTING INFORMATION</h3>
      <div class="btnBox">
        <el-button @click="getLength" type="primary" size="small"
        >get time
        </el-button
        >
        <el-button @click="getCurrentTime" type="primary" size="small"
        >get playback progress
        </el-button
        >
        <el-button @click="getPlayStatus" type="primary" size="small">get playback status</el-button>
    <el-button @click="getAllMusic" type="primary" size="small">getAllMusic</el-button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data () {
    return {
      playstatus: false,
      isDraging: false,
      marks: {
        0: '0',
        100: '100'
      },
      currentRate: 0,
      msg: '待播放',
      // 歌曲名称
      musicName: '祝福你',
      audioPathDic: {
        不再犹豫: require('D:\\音乐\\Beyond不再犹豫.mp3'),
        祝福你: require('D:\\音乐\\Beyond不再犹豫.mp3')
      }
    }
  },
  methods: {
    // 监听audio标签的音频加载完成事件
    loadeddata () {
      // 需等音频加载完成，才能获取到音频的时长
      this.marks['100'] = this.s_to_MS(this.$refs.Ref_audioPlayer.duration)
    },

    // 监听audio标签的播放事件
    onPlay () {
      this.msg = '播放中'
      this.playstatus = true
    },
    // 监听audio标签的暂停事件
    onPause () {
      this.msg = '已暂停'
      this.playstatus = false
    },
    // 监听audio标签的停止播放事件
    overAudio () {
      this.msg = '播放结束'
      this.playstatus = false
    },
    // 监听audio标签的播放进度改变事件
    timeupdate (e) {
      // 使用this.isDraging来控制，当拖拽进度条的过程中，停止对进度的更新——用于解决bug:拖动进度条自动回弹
      if (!this.isDraging) {
        this.currentRate = Math.floor(
          (e.target.currentTime / this.$refs.Ref_audioPlayer.duration) * 100
        )

        this.marks['0'] = this.s_to_MS(e.target.currentTime)
      }
    },

    // 秒数转换分+秒
    s_to_MS (s) {
      let m
      m = Math.floor(s / 60)
      s = Math.floor(s % 60)
      m += ''
      s += ''
      s = s.length === 1 ? '0' + s : s
      if (isNaN(m)) {
        return '&infin;'
      }
      return m + ':' + s
    },

    // 切换音乐
    changeMusice (newMusicName) {
      this.musicName = newMusicName
      this.$nextTick(() => {
        this.$refs.Ref_audioPlayer.play()
      })
    },

    // 原创面板——播放
    play () {
      this.$refs.Ref_audioPlayer.play()
    },

    // 原创面板——暂停
    pause () {
      this.$refs.Ref_audioPlayer.pause()
    },

    // 原创面板——重新播放
    replay () {
      this.$refs.Ref_audioPlayer.currentTime = 0
      this.$refs.Ref_audioPlayer.play()
    },

    // 原创面板——前进、后退——改变当前播放位置
    move (val) {
      this.$refs.Ref_audioPlayer.currentTime += val
      this.$refs.Ref_audioPlayer.play()
    },

    // 原创面板——点击/拖拽原创面板进度条
    currentRateChange (newVal) {
      this.$refs.Ref_audioPlayer.currentTime = Math.round(
        (newVal / 100) * this.$refs.Ref_audioPlayer.duration
      )
      this.$refs.Ref_audioPlayer.play()
    },

    // 获取播放状态
    getPlayStatus () {
      this.$notify.info({
        title: '当前播放状态',
        message: this.$refs.Ref_audioPlayer.paused ? '已暂停' : '播放中'
      })
    },

    // 获取播放进度
    getCurrentTime () {
      // 已播放时长（单位秒s）
      let totalSeconds = this.$refs.Ref_audioPlayer.currentTime
      // 分钟数
      let minites = Math.floor(totalSeconds / 60)
      // 秒数
      let seconds = Math.floor(totalSeconds % 60)

      this.$notify.info({
        title: '当前进度',
        message: `${minites}分${seconds}秒`
      })
    },

    // 获取音频时长
    getLength () {
      // 时长（单位秒s）
      let totalSeconds = this.$refs.Ref_audioPlayer.duration
      // 分钟数
      let minites = Math.floor(totalSeconds / 60)
      // 秒数
      let seconds = Math.floor(totalSeconds % 60)

      this.$notify.info({
        title: '时长',
        message: `${minites}分${seconds}秒`
      })
    },
    getAllMusic () {
      const path = require('D:\\图片\\Saved Pictures\\0.gif')
      // 读取文件的路径 是否遍历文件的子目录 匹配文件正则表达式
      const files = require.context('D:\\图片\\Saved Pictures', false, /\.(png|jpg|gif|jpeg|webp|ico)$/)
      // files(key).default拿到该模块的vue对象
      files.keys().forEach(key => {
        const name = path.basename(key)
        console.log(name)
      })
    }
  }
}
</script>

<style scoped>
.btnBox {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  align-content: space-around;
}

.container {
  text-align: center;
  max-width: 500px;
  margin: auto;
}

.listBox {
  text-align: left;
}

.subTitle {
  margin-left: 40px;
}

.musicItem {
  line-height: 40px;
  cursor: pointer;
}

.myPlayer {
  border: 4px solid #409eff;
  border-radius: 10px;
  padding: 10px 20px;
  margin: 10px;
}

.controlBox {
  display: flex;
  justify-content: space-evenly;
  font-size: 30px;
  color: #409eff;
}

/* 去除手机网页点击事件的阴影 */
* {
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  -webkit-tap-highlight-color: transparent;
}
</style>
