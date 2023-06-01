// pages/recommendSong/recommendSong.js
import request from "../../utils/request";
import requestDB from "../../utils/requestDB"
import PubSub from 'pubsub-js'

let currentSong = {}
const appInstance = getApp();
const userInfo = appInstance.globalData.userInfo
Page({

  /**
   * 页面的初始数据
   */
  data: {
    day: '',
    month: '',
    recommendList: [],//推荐列表数据
    index: 0,//点击音乐的下标
    isShowConfirm: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    //判断用户是否登录
    let userInfo = wx.getStorageSync('userInfo');
    // if (!userInfo) {
    //   wx.showToast({
    //     title: '请先登录',
    //     icon: 'error',
    //     success:()=>{
    //       wx.reLauch({
    //         url: '/pages/login/login'
    //       })
    //     }
    //   })
    // }

    //更新日期的状态数据
    this.setData({
      day: new Date().getDay(),
      month: new Date().getMonth() + 1
    });

    //获取每日推荐的数据
    this.getRecommendList();

    //订阅来自songDetail页面发布的消息
    PubSub.subscribe('switchType', (msg, type) => {
      let {recommendList, index} = this.data;
      if (type === 'pre') {//上一首
        (index === 0) && (index = recommendList.length);
        index -= 1;
      } else {//下一首哦
        (index === recommendList.length - 1) && (index = -1)
        index += 1;
      }

      //更新下标
      this.setData({
        index
      })
      let musicId = recommendList[index].id;
      //将musicId回传给songDetail
      PubSub.publish('musicId', musicId);
    })
  },

  // 显示添加歌曲到歌单列表的 Mode
  showAddSongMode() {
    this.setData({
      isShowConfirm: true
    })
  },

  // 歌曲的更多操作，提示框
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

  // 废弃的方法
  getSongData(e) {
    let song = {}
    let {songlist} = e.detail
    song.songListId = songlist.songListId
    song.userId = userInfo.userId
    song.songId = currentSong.id
    song.songTitle = currentSong.name
    song.songAuthor = currentSong.ar[0].name
    song.songImgUrl = currentSong.al.picUrl
    return JSON.stringify(song)
  },

  // 添加歌曲到歌单的异步请求
  async addSong(e) {
    let {songlist} = e.detail
    let songListId = songlist.songListId
    let userId = userInfo.userId
    let songId = currentSong.id
    let songTitle = currentSong.name
    let songAuthor = currentSong.ar[0].name
    let songImgUrl = currentSong.al.picUrl
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

  //获取用户每日推荐的数据
  async getRecommendList() {
    let recommendListData = await request('/recommend/songs');
    this.setData({
      recommendList : recommendListData.data.dailySongs
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