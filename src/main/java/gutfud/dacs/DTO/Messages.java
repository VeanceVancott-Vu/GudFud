package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;

@Entity
public class Messages {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "ConversationID", nullable = true)
    private Long conversationId;
    @Basic
    @Column(name = "SenderID", nullable = true, length = 100)
    private String senderId;
    @Basic
    @Column(name = "Message_text", nullable = true, length = -1)
    private String messageText;
    @Basic
    @Column(name = "Image", nullable = true)
    private byte[] image;
    @Basic
    @Column(name = "TimeStamp", nullable = true)
    private Timestamp timeStamp;
    @Basic
    @Column(name = "IsRead", nullable = true)
    private Boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", conversationId=" + conversationId +
                ", senderId='" + senderId + '\'' +
                ", messageText='" + messageText + '\'' +
                ", timeStamp=" + timeStamp +
                ", isRead=" + isRead +
                '}';
    }
}
