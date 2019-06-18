package com.tang.hwplib.tools.table

import com.tang.hwplib.objects.HWPDocument
import com.tang.hwplib.objects.bodytext.control.HWPControlTable
import com.tang.hwplib.objects.bodytext.paragraph.HWPParagraphList
import kotlin.text.StringBuilder

class HWPTable(val tableIndex: Int) {
    var rowMap = HashMap<Int, HWPTableRow>()

    fun tableInfo() : Pair<Int, Int> {
        var row = 0
        var column = 0

        if (!rowMap.isEmpty()) {
            row = rowMap.size
            for (key in rowMap.keys) {
                column = rowMap[key]!!.columnCount()
                break
            }
        }

        return Pair(row, column)
    }

    fun putData(row: Int, column: Int, data: String) {
        if (rowMap.containsKey(row)) {
            rowMap[row]!!.putData(column, data)
        } else {
            rowMap[row] = HWPTableRow().apply { putData(column, data) }
        }
    }

    fun getData(row: Int, column: Int) : String {
        if (rowMap.containsKey(row)) {
            return rowMap[row]!!.getData(column)
        }
        return ""
    }

    fun getAllData() : String {
        val builder = StringBuilder()
        for (rKey in rowMap.keys) {
            for (cKey in rowMap[rKey]!!.columnMap.keys) {
                builder.append("[$rKey, $cKey] => ${rowMap[rKey]!!.getData(cKey)}\t")
            }
            //builder.append("\n")
        }
        return builder.toString()
    }
}

class HWPTableRow {
    var columnMap = HashMap<Int, HWPTableColumn>()

    fun columnCount() : Int = columnMap.size

    fun putData(column: Int, data: String) {
        if (!columnMap.containsKey(column))
            columnMap[column] = HWPTableColumn().apply { this.text = data }
    }

    fun getData(column: Int) : String {
        if (columnMap.containsKey(column)) return columnMap[column]!!.text
        return ""
    }
}

class HWPTableColumn {
    lateinit var text: String
}

fun HWPDocument.getTableText() : ArrayList<HWPTable> {
    fun getTextInParagraphList(paragraphList: HWPParagraphList) : String = StringBuilder().apply {
        for (paragraph in paragraphList) {
            append(paragraph.getNormalString()).append("\n")
        }
    }.toString()

    val builder = StringBuilder()
    val tableList = ArrayList<HWPTable>()

    for (section in this.bodyText.sectionList) {
        for (paragraph in section.paragraphList) {
            paragraph.controlList?.let {
                for (control in it) {
                    if (control is HWPControlTable) {
                        val table = HWPTable(tableList.size + 1)
                        builder.append("Table [${control.rowList.size}, ${control.rowList[0].cellList.size}]")
                        for ((rowIndex, row) in control.rowList.withIndex()) {
                            for ((cellIndex, cell) in row.cellList.withIndex()) {
                                table.putData(rowIndex, cellIndex, getTextInParagraphList(cell.paragraphList))
                                builder.append("[$rowIndex, $cellIndex] -> ${getTextInParagraphList(cell.paragraphList)}")
                            }
                        }
                        tableList.add(table)
                    }
                }
            }
        }
    }

    return tableList
}