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
    wx.cloud.callFunction({
      name: "searchMoment",
      success:(res)=>{
      }
    });
  },

  searchMoment(){
    db.collection(MOMENTLIST).get().then(res => {
      // res.data 是一个包含集合中有权限访问的所有记录的数据，不超过 20 条
      console.log(res.data)
      let moments = res.data;
      this.setData({
        moments
      })
    })
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