package hu.sch.kirdev.spoty.model

import javax.persistence.*

@Entity
@Table(name="tracks")
@Suppress("JpaDataSourceORMInspection")
data class MusicEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "`year`") var year: Int = 0,
    var loudness: Float = 0.0f,
    var liveness: Float = 0.0f,
    var tempo: Float = 0.0f,
    var valence: Float = 0.0f,
    var instrumentalness: Float = 0.0f,
    var danceability: Float = 0.0f,
    var speechiness: Float = 0.0f,
    var duration: Int = 0, // TODO: in second
    @Column(name = "`explicit`") var explicit: Int = 0,
    @Column(name = "`mode`") var mode: Int = 0,
    var artists: String = "", // TODO: parse jackson
    var releaseDate: String = "",
    var acousticness: Float = 0.0f,
    var popularity: Int = 0,
    @Column(name = "`name`") var name: String = "",
    var trackId: String = "",
    @Column(name = "`key`") var key: Int = 0,
    var energy: Float = 0.0f,
    var cover: String = ""
)