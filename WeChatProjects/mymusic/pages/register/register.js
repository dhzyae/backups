// pages/register/register.js

import requestDB from '../../utils/requestDB'
let appInstance = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: "",
    password: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  // 表单项内容发生改变的回调
  handleInput(event){
    // let type = event.currentTarget.id;// id传值 取值： phone || password
    let type = event.currentTarget.dataset.type; // data-key=value
    // console.log(event);
    this.setData({
      [type]: event.detail.value
    })
  },

  // 用户注册的异步请求
  // 注册成功会设置用户信息到 Storage
  // 并且跳转到个人中心页面
  async register(){
    // 1. 收集表单项数据
    let {username, password} = this.data;
    // 2. 前端验证

    if(!username){
      // 提示用户
      wx.showToast({
        title: '用户名不能为空',
        icon: 'error'
      })
      return;
    }

    if(!password){
      wx.showToast({
        title: '密码不能为空',
        icon: 'error'
      })
      return;
    }

    // 后端验证
    let result = await requestDB('/registerServlet', {username, password}).then()
    if(result.statusCode === 200){ // 登录成功
      wx.showToast({
        title: '注册成功！',
        icon: 'success'
      })

      // 将用户的信息存储至本地
      console.log(result)
      wx.setStorageSync('userInfo', JSON.stringify(result.data[0]))
      appInstance.globalUpdateUserInfo()
      // 跳转至个人中心personal页面
      wx.reLaunch({
        url: '/pages/personal/personal'
      })
    }else if(result.code === 400){
      wx.showToast({
        title: '网络错误',
        icon: 'error'
      })
    }
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