package com.tang.hwplib.writer.util

import com.tang.hwplib.objects.fileheader.HWPFileVersion
import org.apache.poi.poifs.filesystem.DirectoryEntry
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Microsoft compound File Stream [MS-CFB]를 쓰기 위한 스트림 객체
 * APACHE POI 라이브러리
 * @see [POIFSFileSystem]
 * @see <link href='https://msdn.microsoft.com/ko-kr/library/dd942138.aspx'>
 *
 * @author accforaus
 *
 * @property [fs] [POIFSFileSystem], 파일 시스템
 * @property [currentStorage] [DirectoryEntry], 현재 스토리지(디렉토리)
 * @property [currentStreamWriter] [StreamWriter], 현재 스트림을 쓰기 위한 객체
 */
internal class CompoundFileWriter {
    var fs: POIFSFileSystem = POIFSFileSystem()
    var currentStorage: DirectoryEntry = fs.root
    var currentStreamWriter: StreamWriter? = null

    /**
     * 파일 시스템에 저장된 데이터를 파일로 저장하는 함수
     *
     * @param [filePath] [String], 저장될 파일 경로
     */
    fun write(filePath: String) {
        val os: OutputStream = FileOutputStream(filePath)
        fs.writeFilesystem(os)
        os.close()
    }

    /**
     * 파일 시스템을 닫는 함수
     */
    fun close() { fs.close() }

    /**
     * 현재 스토리지(디렉토리)를 생성하는 함수
     *
     * @param [name] [String], 스토리지 이름
     */
    fun openCurrentStorage(name: String) {
        currentStorage = currentStorage.createDirectory(name)
    }

    /**
     * 현재 스토리지(디렉토리)를 닫는 함수
     */
    fun closeCurrentStorage() {
        currentStorage = currentStorage.parent
    }

    /**
     * 열린 스토리지에 스트림을 생성하고 반환하는 함수
     *
     * @param [name] [String], 스트림 이름
     * @param [compress] [Boolean], 압축 여부
     * @param [fileVersion] [HWPFileVersion], 한글 문서의 파일 버전
     *
     * @return [StreamWriter] 스트림을 쓰기 위한 객체 반환 (존재하지 않으면 NULL)
     */
    fun openCurrentStream(name: String, compress: Boolean, fileVersion: HWPFileVersion) : StreamWriter? {
        currentStreamWriter = StreamWriter(name, compress, fileVersion)
        return currentStreamWriter
    }

    /**
     * 열려있는 스트림에 있는 데이터를 현재 스토리지에 저장하고 닫는 함수
     */
    fun closeCurrentStream() {
        val inputStream: InputStream = currentStreamWriter!!.getDataStream()
        currentStorage.createDocument(currentStreamWriter!!.name, inputStream)
        inputStream.close()
        currentStreamWriter!!.close()
        currentStreamWriter = null
    }

    /**
     * root 스토리지로 이동하는 함수
     */
    fun gotoRootStorage() {
        currentStorage = fs.root
    }
}