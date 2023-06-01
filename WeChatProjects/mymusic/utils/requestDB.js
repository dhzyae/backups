// 发送ajax请求
import config from './config'

export default (url, data={}, method='GET') => {
    return new Promise((resolve, reject) => {
      wx.request({
        url:config.localhost + url,
        data,
        method,
        header: {
        },
        success: (res) => {
            // console.log('请求成功', res);
            resolve(res);
        },
        fail:(err) => {
            console.log('请求失败', err);
            reject(err);
        }
      })  
    })
}