import request from '../../utils/request'

// pages/index/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    bannerList: [], // 轮播图数据
    recommendList: [],  // 推荐歌单
    topList: [],  // 排行榜数据
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: async function(options) {
    let bannerListData = await request('/banner',{type: 2});
    this.setData({
      bannerList: bannerListData.banners
    })
    // console.log("结果数据：", bannerListData);

    // 获取歌单数据
    let recommendListData = await request('/personalized', {limit: 10});
    this.setData({
      recommendList: recommendListData.result
    })

    //获取排行榜数据
    // let topListArr = [];
    // let topListId = [19723756, 3779629, 2884035, 3778678, 5453912201]
    const arr = [
      this.getTopList(19723756),
      this.getTopList(3779629),
      this.getTopList(2884035),
      this.getTopList(3778678),
      this.getTopList(5453912201)
    ]
    Promise.all(arr).then(result => {
      this.setData({
        topList: result
      })
    })
  },

  async getTopList(id) {
    let topListData = await request('/playlist/detail', {id: id}).then();
    return {name: topListData.playlist.name, tracks: topListData.playlist.tracks.slice(0, 3)};
  },

  // 跳转到推荐歌单
  toHotList(e) {
    let { listid } = e.currentTarget.dataset;
    wx.navigateTo({
      url: `/pages/hotList/hotList?listid=${listid}`,
    })
  },

  //跳转至recommendSong页面的回调
  toRecommendSong() {
    wx.navigateTo({
      url: '/pages/recommendSong/recommendSong'
    })
  },

  // 跳转到search也页面
  toSearchPage(){
    wx.navigateTo({
      url: '/pages/search/search'
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