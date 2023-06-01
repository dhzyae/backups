// app.js
App({
  onLaunch() {
    
    wx.showModal({
      title: '点击小鸟背景图分享歌曲',
    })
    
    // 登录
    wx.login({
      success: res => {
        res.code
      }
    })
  },  

  globalData: {
    userInfo: null
  }
})
