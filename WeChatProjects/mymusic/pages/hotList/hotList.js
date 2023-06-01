import request from "../../utils/request"

// pages/hotList/hotList.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    songList: [],//歌单列表
    playListDetail: {}//歌单详情
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.initList(options.listid)
    this.getPlayListDetail(options.listid)
  },

  async getPlayListDetail(listid){
    let playListDetail = await request(`/playlist/detail?id=${listid}`)
    this.setData({
      playListDetail
    })
  },

  async initList(listid) {
    let songList = []
    console.log(listid)
    let result1 = await request(`/playlist/track/all?id=${listid}&limit=10&offset=1`)
    console.log(result1.songs)
    songList.push(...result1.songs)
    let result2 = await request(`/playlist/track/all?id=${listid}&limit=10&offset=10`)
    songList.push(...result2.songs)
    this.setData({
      songList
    })
  },

   //跳转到songDetail页面
   toSongDetail(event) {
    let {song, index} = event.currentTarget.dataset;
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