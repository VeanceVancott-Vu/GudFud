package gutfud.dacs.DTO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Conversations {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "User1ID", nullable = true, length = 100)
    private String user1Id;
    @Basic
    @Column(name = "User2ID", nullable = true, length = 100)
    private String user2Id;
    @Basic
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    public Long  getId() {
        return id;
    }

    public void setId(Long  id) {
        this.id = id;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
