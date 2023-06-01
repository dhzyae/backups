// pages/songListDetail/songListDetail.js
import requestDB from "../../utils/requestDB"

let currentSong = {}
const appInstance = getApp();
const userInfo = appInstance.globalData.userInfo

Page({

  /**
   * 页面的初始数据
   */
  data: {
    songList: [],// 歌单详情
    isShowConfirm: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let songListId = options.songListId
    this.updateListDetail(songListId)
  },

  // 更新歌单详情的异步请求
  async updateListDetail(songListId){
    let result = await requestDB('/searchListDetailServlet', {songListId}).then()
    console.log(result)
    this.setData({
      songList: result.data
    })
    return result
  },

  // 显示歌单列表的 Mode
  showAddSongMode() {
    this.setData({
      isShowConfirm: true
    })
  },

  // 显示更多操作的 Tip
  showMoreMode(event){
    currentSong = event.currentTarget.dataset.song
    console.log(currentSong)
    let that = this;
    wx.showActionSheet({
      itemList: ['下一首播放', '添加到歌单', '分享到社区'],
      success (res) {
        console.log(res.tapIndex)
        switch(res.tapIndex){
          case 0:
            break
          case 1:
            that.showAddSongMode();
            break
          case 2:
            break
        }
      },
      fail (res) {
        console.log(res.errMsg)
      }
    })
  },


  // 添加歌曲到歌单的异步请求
  async addSong(e) {
    let {songlist} = e.detail
    let songListId = songlist.songListId
    let userId = userInfo.userId
    let songId = currentSong.songId
    let songTitle = currentSong.songTitle
    let songAuthor = currentSong.songAuthor
    let songImgUrl = currentSong.songImgUrl
    let result = await requestDB('/addSongServlet', {songListId,userId,songId,songTitle,songAuthor,songImgUrl}).then()
    this.cancel()
    if(result.statusCode = 200) {
      wx.showToast({
        title: '添加成功',
        icon: 'success'
      })
    }
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

  // 删除歌曲的提示
  async deleteTip(event){
    let that = this
    wx.showModal({
      title: '移除歌曲',
      content: '请确认是否在该歌单中移除此歌曲',
      confirmText: '确认',
      showCancel: 'true',
      confirmColor: '#D84E43',
      success (res) {
        if (res.confirm) {
          let {song} = event.currentTarget.dataset;
          let songListId = song.songListId
          let songId = song.songId
          that.deleteSong(songListId, songId).then(b=>{
            if(b.statusCode === 200) {
              wx.showToast({
                title: '删除成功',
                icon: 'success'
              })
              that.updateListDetail(songListId)
            }else {
              wx.showToast({
                title: '删除失败',
                icon: 'error'
              })
            }
          })
          
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
    
  },

  // 删除歌曲的异步请求
  async deleteSong(songListId, songId) {
    return await requestDB('/deleteSongServlet', {songListId, songId}).then()
  },

  //跳转到songDetail页面
  toSongDetail(event) {
    let {song} = event.currentTarget.dataset;
    console.log(event.currentTarget.dataset)
    this.setData({
      index
    })
    //路由跳转传参
    wx.navigateTo({
      url: '/pages/songDetail/songDetail?musicId=' + song.id
    })
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