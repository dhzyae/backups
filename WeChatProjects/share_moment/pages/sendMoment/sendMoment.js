// pages/sendMoment/sendMoment.js

const MOMENTLIST = "moment_list";
wx.cloud.init({
  env: 'cloud1-6grx1ab608d9bb1c'
})
const db = wx.cloud.database();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    content: "分享了一首歌曲",//分享的内容
    singer: "",//歌手名字
    title: "",//歌曲名
    imgUrl: "",//歌曲封面tempFileUrl
    imgId: ""//歌曲封面fileID
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  sendMoment(e) {
    this.addToMoment(
      this.data.content,
      this.data.singer,
      this.data.title,
      "nickname",
      this.data.imgUrl,
      this.data.imgId
    )
  },

  addToMoment(content,singer,title,nickname,imgUrl,imgId){
    db.collection(MOMENTLIST).add({
      data: {
        detail_text: content,
        singer: singer,
        song_title: title,
        username: nickname,
        img_url: imgUrl,
        img_id: imgId
      },
      success: () => {
        this.goBack()
      },
      fail: () => {
        console.log("上传失败！")
      }
    })
  },

  chooseImg() {
    wx.chooseMedia({
      count:1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success: chooseResult => {
        // 将图片上传至云存储空间
        // console.log(chooseResult.tempFiles[0].tempFilePath)//小程序临时路径名
        this.uploadImage(chooseResult.tempFiles[0].tempFilePath)
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
    console.log(fileId)
    wx.cloud.getTempFileURL({
      fileList: [fileId],
      success: res => {
        // console.log("获取url地址：",res);
        this.setData({
          imgUrl: res.fileList[0].tempFileURL,
          imgId: res.fileList[0].fileID
        })
        wx.showToast({
          title: '添加成功',
          icon: "success",
          duration: 1000
        })
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

  //插入成功的返回回调方法
  goBack(){
    wx.showToast({
      title: '发送成功',
      icon: "success",
      duration: 1500
    })
    setTimeout(function(){
      wx.redirectTo({
        url: "/pages/index/index"
      })
    },1500
    )
  },

  fakeCallback(){},//双向绑定的垃圾回调


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})