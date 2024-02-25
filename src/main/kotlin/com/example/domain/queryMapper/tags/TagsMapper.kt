package com.example.domain.queryMapper.tags

import com.example.data.dto.tags.TagsDto
import com.example.data.tables.TagsTable
import org.ktorm.dsl.QueryRowSet

fun QueryRowSet.tagsTableToTagsDtoMapper() : TagsDto{
    return TagsDto(
            tag_id = this[TagsTable.id]?:0,
            tag_name = this[TagsTable.tag_name]?:""
    )
}