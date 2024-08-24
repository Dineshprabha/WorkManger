package com.dinesh.foregroundservices.data.mappers

import com.dinesh.foregroundservices.data.model.QuoteDTO
import com.dinesh.foregroundservices.domain.model.Quote

fun QuoteDTO.toDomain(workType : String) : Quote{
    return Quote(author, id, quote, workType)
}