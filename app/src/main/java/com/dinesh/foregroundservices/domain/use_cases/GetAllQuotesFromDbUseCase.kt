package com.dinesh.foregroundservices.domain.use_cases

import com.dinesh.foregroundservices.domain.repository.QuoteRepository
import javax.inject.Inject

class GetAllQuotesFromDbUseCase @Inject constructor(
    private val quoteRepository: QuoteRepository
) {
    operator fun invoke() = quoteRepository.getAllQuotes()
}