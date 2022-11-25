package com.magicpark.api.services
import com.magicpark.api.model.database.UserTicket
import com.magicpark.api.repositories.IUserTicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class WalletService(val walletRepository: IUserTicketRepository) {
    fun getWalletById(id: Long): ResponseEntity<Any> {
       val result = walletRepository.findByUserId(id)
        return if (result.isPresent) {
            val userTicket = result.get()
            ResponseEntity.status(HttpStatus.OK).body(userTicket)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}