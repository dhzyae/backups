// app.js
import touch from './utils/touch.js'
import requestDB from './utils/requestDB'
App({
  globalData:{
    isMusicPlay: false,//是否有音乐在播放
    musicId: '',//音乐id
    userInfo:{},
  },
  touch:new touch(),//实例化touch对象
  onLaunch() {
    this.globalUpdateUserInfo()
  },
  globalUpdateUserInfo() {
    let userInfo = JSON.parse(wx.getStorageSync('userInfo'))
    this.globalData.userInfo = userInfo
    if(userInfo){ // 用户登录
      // 更新userInfo的状态
      return userInfo
    }
  },
  async addMoment(momnet) {
    let userId = momnet.userId
    let username = momnet.username
    let avatarUrl = momnet.avatarUrl
    let content = momnet.content
    let songTitle = momnet.songTitle
    let songAuthor = momnet.songAuthor
    let songImgUrl = momnet.songImgUrl
    console.log(momnet)
    return await requestDB('/shareSongServlet', {userId,username,avatarUrl,content,songTitle,songAuthor,songImgUrl}).then()
  }
})
