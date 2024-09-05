package com.myTemplateProject.service.socketIoService;

import com.alibaba.fastjson.JSON;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class SocketIoService {

    //key：sessionId，value：SocketIOClient
    static ConcurrentHashMap<String, SocketIOClient> clients=new ConcurrentHashMap<>();
    @Autowired
    private SocketIOServer socketIOServer;

    @Autowired
    Map<Long, Date> onlineUserMap;

    @PostConstruct
    private void autoStart(){
        log.info("socketIo auto start");
        start();
    }

    @PreDestroy
    private void autoStop(){
        stop();
    }

    private void stop() {
        if(socketIOServer!=null){
            socketIOServer.stop();
            socketIOServer=null;
        }
    }

    private void start() {

        socketIOServer.addConnectListener(client -> {

            Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
            clients.put(client.getSessionId().toString(),client);
            log.info("sessionId:"+client.getSessionId()+"加入");

        });

        socketIOServer.addDisconnectListener(client->{
            String id = client.getSessionId().toString();
            clients.remove(id);
            log.info("sessionId:"+client.getSessionId()+"退出");
        });

//        socketIOServer.addEventListener(SocketIoEvents.RECEIVE_MESSAGE, JSONObject.class,(client, data, ackSender)->{
//
//            Map<String, List<String>> urlParams = client.getHandshakeData().getUrlParams();
//            // 根据参数名获取对应的值
//            log.info(urlParams.toString());
//
//            String id = client.getSessionId().toString();
//            String userName = sessionId_user.get(id).getUserName();
//            MessagePojo pojo = JSONObject.toJavaObject((JSONObject)data, MessagePojo.class);
//            log.info("收到用户"+userName+"发送的消息："+pojo.getContent());
//            Message message = insertMessageAtSocket(pojo);
//
//            User sender = userService.queryUserById(sessionId_user.get(id).getId());
//            User receiver=null;
//            if(!ObjectUtils.isEmpty(pojo.getReceiveUserId())){
//                receiver = userService.queryUserById(pojo.getReceiveUserId());
//            }
//            MessageVo vo = MessageVo.builder().sendUser(sender)
//                    .receiveUser(receiver)
//                    .messageContent(message.getContent())
//                    .messageId(message.getId())
//                    .date(message.getDate())
//                    .build();
//            sendBroadCastMessage(vo,SocketIoEvents.SEND_MESSAGE);
//        });
        socketIOServer.start();
    }

    /**
     * socket 广播方法
     * @param data
     * @param eventName
     */
    public void sendBroadCastMessage(Object data,String eventName){
        if(ObjectUtils.isEmpty(data)){
            log.info("推送内容为空");
            return;
        }
        socketIOServer.getBroadcastOperations().sendEvent(eventName, JSON.toJSONString(data));
    }

    /**
     * socket 定向发送方法
     * @param data
     * @param eventName
     */
    public void sendGroupMessage(Object data, String eventName,Collection<String> sessionIds){
        if(ObjectUtils.isEmpty(data)){
            log.info("推送内容为空");
            return;
        }
        for( String sessionId:sessionIds){
            SocketIOClient client = clients.get(sessionId);
            if(!ObjectUtils.isEmpty(client)){
                client.sendEvent(eventName,JSON.toJSONString(data));
            }
        }
    }

    //socket没有requestHeader，拿不到token (暂时不知道如何获取header）
//    @Transactional
//    public Message insertMessageAtSocket(MessagePojo messagePojo) {
//        Message message=new Message();
//        message.setSendUserId(messagePojo.getSendUserId());
//        message.setContent(messagePojo.getContent());
//        message.setDate(new Date());
//        message.setReceiveUserId(CommonUtils.getObjectOrNull(messagePojo.getReceiveUserId()));
//        message.setReplyMessageId(CommonUtils.getObjectOrNull(messagePojo.getReplyMessageId()));
//        message.setIsBroadcast(!ObjectUtils.isEmpty(messagePojo.getIsBroadcast()?messagePojo.getIsBroadcast():true));
//        messageMapper.insert(message);
//        return message;
//    }
}
