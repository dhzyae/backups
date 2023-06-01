// pages/index/index.js
import request from '../../utils/request';
import config from '../../utils/config'
import touch from '../../utils/touch'

const EN = 'en';
const CH = 'zh-CHS';
let globalWordsList = [];
const App = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShowResult: true,//查询结果列表的的显示
    isShowMyWords: false,
    queryList: [],//查询结果列表
    myWordsList: [],//添加到列表的单词
    queryInput: '',
    touch: new touch()
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let wordsList = wx.getStorageSync('words')
    if(wordsList) {
      let myWordsList = JSON.parse(wordsList)
      globalWordsList = myWordsList
      this.setData({
        myWordsList
      })
    }
  },

  touchstart: function (e) {
    //开始触摸时 重置所有删除
    let data = App.touch._touchstart(e, this.data.list) //将修改过的list setData
    console.log('000')
  },

  //滑动事件处理
  touchmove: function (e) {
    let data = App.touch._touchmove(e, this.data.list,'id')//将修改过的list setData
    console.log('111')
  },

  //移除我的列表里的单词
  removeWords(e) {
    let index = e.currentTarget.dataset.index
    // console.log(index)
    globalWordsList.splice(index, 1)
    this.setData({
      myWordsList: globalWordsList
    })
    wx.setStorageSync('words', JSON.stringify(globalWordsList))
  },

  //读单词的回调
  playSound(e) {
    let words = e.currentTarget.dataset.words
    const innerAudioContext = wx.createInnerAudioContext({
      useWebAudioImplement: true
    })
    innerAudioContext.src = words.speakUrl//设置音频链接
    innerAudioContext.play()//播放
  },

  //添加到我的单词列表
  addWord(e) {
    let words = e.currentTarget.dataset.words
    globalWordsList.unshift(words)
    wx.setStorageSync('words', JSON.stringify(globalWordsList))
    this.setData({
      myWordsList: globalWordsList
    })
    this.setShowMyWords()
  },

  //搜索按钮的回调
  async search() {
    let q = this.data.queryInput
    let queryItem = {}
    let queryList = []
    let result = await request({q:q, from:EN, to:CH})
    if(result.translation) {
      queryItem.translation = result.translation
      queryItem.query = result.query
      queryItem.speakUrl = result.speakUrl
      queryList.push(queryItem)
    }
    this.setData({
      queryList
    })
    this.setShowResult()
  },

  //监听输入框改变值的回调
  setValue(e) {
    this.setData({
      queryInput: e.detail.value
    })
  },

  //切换界面显示
  setShowMyWords() {
    this.setData({
      isShowResult: true,
      isShowMyWords: false
    })
  },
  setShowResult() {
    this.setData({
      isShowResult: false,
      isShowMyWords: true
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