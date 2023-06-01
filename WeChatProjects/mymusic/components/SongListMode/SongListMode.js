// components/SongListMode/SongListMode.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    musicLibrary: []
  },

  /**
   * 组件的方法列表
   */
  methods: {
    cancel() {
      this.triggerEvent('cancel')
    },
    addSong(event) {
      this.triggerEvent('addSong', event.currentTarget.dataset)
    }
  },

  // pageLifetimes: {
  //   show: function() {
  //     // 页面被展示
  //     console.log(111)
  //   },
  //   hide: function() {
  //     // 页面被隐藏
  //   },
  //   resize: function(size) {
  //     // 页面尺寸变化
  //   }
  // },

  lifetimes: {
    attached: function() {
      // 在组件实例进入页面节点树时执行
      let musicLibrary = wx.getStorageSync('musicLibrary')
      this.setData({
        musicLibrary
      })
    },
    detached: function() {
      // 在组件实例被从页面节点树移除时执行
    },
  },

  
})
