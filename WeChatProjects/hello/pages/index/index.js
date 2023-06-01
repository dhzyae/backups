let countdown = {};

Page({
  /**
   * 页面的初始数据
   */
  data: {
    countdown: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.updateClock();
  },

  updateClock() {
    var that = this;
    setInterval(function() {
      var nowTime = new Date();//获取当前时间
      //创建日期对象
      var endTime = new Date("2023-1-1 00:00:00");
      var seconds = parseInt((endTime.getTime() - nowTime.getTime()) / 1000);//两个时间点的时间差(秒)
      var d = parseInt(seconds / 3600 / 24);//得到天数
      var h = parseInt(seconds / 3600 % 24);//小时
      var m = parseInt(seconds / 60 % 60);//分钟
      var s = parseInt(seconds % 60);//秒
      countdown.d = d;
      countdown.h = h;
      countdown.m = m;
      countdown.s = s;
      that.setData({
        countdown
      })
    }, 1000);
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