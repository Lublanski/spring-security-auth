package ai.gobots.authentication.infrastucture.configuration.exception

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorMessage(
    val status: Int,
    val detail: String?,
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val errors: Map<String, String>? = null
)
