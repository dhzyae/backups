// 发送ajax请求
import config from './config'
import request from 'request';
import CryptoJS from 'crypto-js'

export default (query={}, method='GET') => {
  let info = getInfo(query.q);
  return new Promise((resolve, reject) => {
    wx.request({
      url:config.url,
      dataType: 'jsonp',
      data: {
        q: query.q,
        appKey: config.appKey,
        salt: info.salt,
        from: query.from,
        to: query.to,
        sign: info.sign,
        signType: "v3",
        curtime: info.curtime,
        vocabId: info.vocabId,
      },
      method,
      success: (res) => {
        // console.log('请求成功', res);
        resolve(JSON.parse(res.data));
      },
      fail:(err) => {
        console.log('请求失败', err);
        reject(err);
      }
    })  
  })
}

function getInfo(query) {
  var salt = (new Date).getTime();
  var curtime = Math.round(new Date().getTime()/1000);
  var query = query;
  var str1 = config.appKey + truncate(query) + salt + curtime + config.key;
  var vocabId =  '您的用户词表ID';
  var sign = CryptoJS.SHA256(str1).toString(CryptoJS.enc.Hex);
  let data = {
    salt, curtime, vocabId, sign
  }
  return data
};

function truncate(q){
  var len = q.length;
  if(len<=20) return q;
  return q.substring(0, 10) + len + q.substring(len-10, len);
}