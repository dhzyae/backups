import request from "../../utils/request";
import requestDB from '../../utils/requestDB'

wx.cloud.init({
  env: 'mymusic-2gdqfq6l3e4b1b12'
})

let startY = 0; // 手指起始的坐标
let moveY = 0; // 手指移动的坐标
let moveDistance = 0; // 手指移动的距离
Page({

  /**
   * 页面的初始数据
   */
  data: {
    coverTransform: 'translateY(0)',
    coveTransition: '',
    userInfo: {}, // 用户信息
    recentPlayList: [], // 用户播放记录
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // // 读取用户的基本信息
    let userInfo = wx.getStorageSync('userInfo')
    if(userInfo){ // 用户登录
      // 更新userInfo的状态
      this.setData({
        userInfo: JSON.parse(userInfo)
      })
      
    //   // 获取用户播放记录
    //   this.getUserRecentPlayList(this.data.userInfo.userId)
    }
  },
  // 获取用户播放记录的功能函数
  // async getUserRecentPlayList(userId){
  //   let recentPlayListData = await request('/user/record', {uid: userId, type: 0});
  //   let index = 0;
  //   let recentPlayList = recentPlayListData.allData.splice(0, 10).map(item => {
  //     item.id = index++;
  //     return item;
  //   })
  //   this.setData({
  //     recentPlayList
  //   })
  // },
  
  handleTouchStart(event){
    this.setData({
      coveTransition: ''
    })
    // 获取手指起始坐标
    startY = event.touches[0].clientY;
  },
  handleTouchMove(event){
    moveY = event.touches[0].clientY;
    moveDistance = moveY - startY;
    
    if(moveDistance <= 0){
      return;
    }
    if(moveDistance >= 80){
      moveDistance = 80;
    }
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(${moveDistance}rpx)`
    })
  },
  handleTouchEnd(){
    // 动态更新coverTransform的状态值
    this.setData({
      coverTransform: `translateY(0rpx)`,
      coveTransition: 'transform 1s linear'
    })
  },

  /********************************************************************************************************* */
  // 更换头像的异步请求
  async changeAvatar(userId, avatarUrl){
    let result = await requestDB('/updateAvatarServlet', {userId, avatarUrl})
    return result
  },

  // 选择图片，上传云端，并更新用户头像（微信小程序的 api）
  // 选择完图片执行 uploadImage 方法，上传到微信云，
  // 上传完成执行 addImagePath 方法，返回用户上传头像的 url
  chooseImg() {
    let userId = this.data.userInfo.userId
    if(userId == null) {
      this.toLogin()
      return
    }
    wx.chooseMedia({
      count:1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success: chooseResult => {
        // 将图片上传至云存储空间
        // console.log(chooseResult.tempFiles[0].tempFilePath)//小程序临时路径名
        this.uploadImage(chooseResult.tempFiles[0].tempFilePath)
        wx.showLoading({
          title: '上传中',
        })
        setTimeout(() => {
          wx.hideLoading()
        }, 6000);
      },
      fail: () => {
        wx.showToast({
          title: '你未选择图片',
          icon: "error",
          duration: 1000
        })
      }
    })
  },

  // 上传到云开发的存储中
  uploadImage(fileURL) {
    wx.cloud.uploadFile({
      cloudPath:new Date().getTime()+'.png', // 上传至云端的路径
      filePath: fileURL, // 小程序临时文件路径
      success: res => {
        //获取图片的http路径
        // console.log(res)
        this.addImagePath(res.fileID)  // res.fileID 是上传图片的 fileID
      },
      fail: console.error
    })
  },

 // 获取图片上传后的https的url路径地址  参数是上传图片的 fileId
  addImagePath(fileId) {
    wx.cloud.getTempFileURL({
      fileList: [fileId],
      success: res => {
        // return res.fileList[0].tempFileURL
        // console.log("获取url地址：",res.fileList[0])
        let userInfo = JSON.parse(wx.getStorageSync('userInfo'))
        let avatarUrl = res.fileList[0].tempFileURL
        userInfo.avatarUrl = avatarUrl
        wx.setStorageSync("userInfo", JSON.stringify(userInfo))
        this.setData({
          userInfo
        })
        this.changeAvatar(userInfo.userId, userInfo.avatarUrl).then((b)=>{
          let code = b.statusCode
          wx.hideLoading()
          if(code === 200) {
            wx.showToast({
              title: '添加成功',
              icon: 'success',
              duration: 1000
            })
          }else {
            wx.showToast({
              title: '添加失败',
              icon: 'error',
              duration: 1000
            })
        }
        })
        
        // this.setData({
        //   imgUrl: res.fileList[0].tempFileURL,
        //   imgId: res.fileList[0].fileID
        // })
        
      },
      fail: () => {
        wx.showToast({
          title: '添加失败',
          icon: "error",
          duration: 1000
        })
      }
    })
  },
  
  // 跳转至登录login页面的回调
  toLogin(){
    wx.navigateTo({
      url: '/pages/login/login'
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
