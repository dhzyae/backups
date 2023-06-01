// pages/index/index.js

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
      moments: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.searchMoment();
  },

  searchMoment(){
    wx.cloud.callFunction({
      name: "searchmoment",
      success:(res)=>{
        // console.log("云函数调用成功！")
        console.log(res.result)
        this.setData({
          moments: res.result
        })
      }
    });
  },

  
  onRefresh(){
      //导航条加载动画
      wx.showNavigationBarLoading();
      setTimeout(function () {
        wx.hideNavigationBarLoading();
        //停止下拉刷新
        wx.stopPullDownRefresh();
      }, 2000);
    },
  


  toSendMomentPage(){
    wx.navigateTo({
      url: '/pages/sendMoment/sendMoment'
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
    this.onRefresh();
    this.onLoad();
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