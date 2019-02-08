package com.tang.hwplib.reader.util

import com.tang.hwplib.objects.fileheader.HWPFileVersion
import com.tang.hwplib.util.exceptions.StreamReaderException
import org.apache.poi.poifs.filesystem.DirectoryEntry
import org.apache.poi.poifs.filesystem.DocumentEntry
import org.apache.poi.poifs.filesystem.Entry
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

internal class CompoundFileReader {
    private var fs: POIFSFileSystem
    private var currentStorage: DirectoryEntry

    constructor(file : File) {
        fs = try { POIFSFileSystem(file) } catch (e: IOException) { throw StreamReaderException("On error reading", e) }
        currentStorage = fs.root
    }

    constructor(inputStream: InputStream) {
        fs = try { POIFSFileSystem(inputStream) } catch (e: IOException) { throw StreamReaderException("On error reading", e) }
        currentStorage = fs.root
    }

    fun listChildNames(): Set<String> = currentStorage.entryNames

    fun isChildStorage(name: String) : Boolean = try { currentStorage.hasEntry(name) && currentStorage.getEntry(name).isDirectoryEntry } catch(e: FileNotFoundException) { throw StreamReaderException("Storage has not $name storage", e)}

    fun isChildStream(name: String) : Boolean = try { currentStorage.hasEntry(name) && currentStorage.getEntry(name).isDocumentEntry } catch(e: FileNotFoundException) { throw StreamReaderException("Storage has not $name streams", e)}

    fun moveChildStorage(name: String) {
        val e: Entry = try { currentStorage.getEntry(name) } catch (e: FileNotFoundException) { throw StreamReaderException("This is not a storage", e)}
        when(e is DirectoryEntry) {
            true -> currentStorage = e
            else -> throw StreamReaderException("this is not storage.")
        }
    }

    fun moveParentStorage() {
        if (currentStorage != fs.root)
            currentStorage = currentStorage.parent
    }

    fun getChildStreamReader(name: String, compress: Boolean, fileVersion: HWPFileVersion?) : StreamReader = try { currentStorage.getEntry(name).run {
        when (this is DocumentEntry) {
            true -> when (compress) {
                true -> StreamReaderForCompress(this, fileVersion)
                else -> StreamReaderForNormal(this, fileVersion)
            }
            else -> throw StreamReaderException("This is not stream")
        }
    } } catch (e: FileNotFoundException) { throw StreamReaderException("this is not storage", e)}

    fun close() {
        try {
            fs.close()
        } catch (e: IOException) {
            throw StreamReaderException(e.message ?: "", e)
        }
    }
}