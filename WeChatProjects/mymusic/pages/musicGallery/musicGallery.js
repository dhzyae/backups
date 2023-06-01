// pages/searchList/searchList.js
import config from "../../utils/config";
import requestDB from "../../utils/requestDB";
const appInstance = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShowConfirm: false,
    isShowMode: false,
    songListTitle: "",
    userInfo:{},
    musicLibrary: [0],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let userInfo = appInstance.globalData.userInfo
    this.setData({
      userInfo
    })
    this.updateSongList(userInfo.userId).then();
  },

  toDelete(event) {
    let {songlist} = event.currentTarget.dataset
    let songListId = songlist.songListId
    let that = this
    wx.showModal({
      title: '删除歌单',
      content: '是否要删除整个歌单，删除将会清空歌单并无法找回',
      confirmText: '删除',
      showCancel: 'true',
      confirmColor: '#D84E43',
      success (res) {
        if (res.confirm) {
          that.deleteSongList(songListId).then(b=>{
            that.updateSongList(that.data.userInfo.userId)
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },

  async deleteSongList(songListId) {
    return await requestDB('/deleteSongListServlet', {songListId}).then()
  },

  toListDetail(event) {
    let {songlist} = event.currentTarget.dataset
    wx.navigateTo({
      url: '/pages/songListDetail/songListDetail?songListId=' + songlist.songListId
    })
  },

  toLikeList(){
    let musicLibrary = wx.getStorageSync('musicLibrary')
    let songList = musicLibrary[0]
    wx.navigateTo({
      url: '/pages/songListDetail/songListDetail?songListId=' + songList.songListId
    })
  },

  async updateSongList(userId) {
    let result = await requestDB('/searchAllSongListServlet', {userId}).then()
    let musicLibrary = result.data;
    wx.setStorageSync('musicLibrary', musicLibrary)
    musicLibrary.splice(0,1);
    this.setData({
      musicLibrary
    })
    return result
  },

  // 添加歌单的按钮，显示输入框，
  addButton() {
    this.setData({
      isShowConfirm: true
    })
  },

  // 获取用户输入的值
  setValue(e) {
    this.setData({
      songListTitle: e.detail.value
    })
  },
  // 添加歌单Model，取消的回调
  cancel() {
    this.setData({
      isShowConfirm: false,
    })
  },
  // 添加歌单Model，确认的回调
  confirmAcceptance(){
    let {userInfo, songListTitle} = this.data
    this.addSongList(userInfo.userId, songListTitle)
    this.cancel()
  },

  // 添加歌单到数据库的异步请求
  async addSongList(userId, songListTitle) {
    let result = await requestDB('/addSongListServlet', {userId, songListTitle}).then()
    this.updateSongList(userId)
    return result
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    let userInfo = appInstance.globalData.userInfo
    this.setData({
      userInfo
    })
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