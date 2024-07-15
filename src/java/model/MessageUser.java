/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 *
 * @author quoch
 */
public class MessageUser {
    private String messageContent;
    private Timestamp timeStamp;
    private String senderImageAvatar;
    private String receiverImageAvatar;
    private int senderId;
    private int receiverId;

    public MessageUser() {
    }

    public MessageUser(String messageContent, Timestamp timeStamp, String senderImageAvatar, String receiverImageAvatar, int senderId, int receiverId) {
        this.messageContent = messageContent;
        this.timeStamp = timeStamp;
        this.senderImageAvatar = senderImageAvatar;
        this.receiverImageAvatar = receiverImageAvatar;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSenderImageAvatar() {
        return senderImageAvatar;
    }

    public void setSenderImageAvatar(String senderImageAvatar) {
        this.senderImageAvatar = senderImageAvatar;
    }

    public String getReceiverImageAvatar() {
        return receiverImageAvatar;
    }

    public void setReceiverImageAvatar(String receiverImageAvatar) {
        this.receiverImageAvatar = receiverImageAvatar;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "MessageUser{" + "messageContent=" + messageContent + ", timeStamp=" + timeStamp + ", senderImageAvatar=" + senderImageAvatar + ", receiverImageAvatar=" + receiverImageAvatar + ", senderId=" + senderId + ", receiverId=" + receiverId + '}';
    }

    
    
    public String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(timeStamp);
    }
}
