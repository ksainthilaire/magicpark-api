package com.magicpark.api.repositories

import com.magicpark.api.model.database.Voucher
import org.springframework.data.jpa.repository.JpaRepository

interface IVoucherRepository : JpaRepository<Voucher, Long> {
}
