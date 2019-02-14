package com.tang.hwplib.util.exceptions

/**
 * 스트림 리더 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
class StreamReaderException: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause) {
        cause.printStackTrace()
    }
}

/**
 * HWP 문서 리더 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
internal class HWPReadException: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause) {
        cause.printStackTrace()
    }
}

/**
 * 스트림 쓰기 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
internal class StreamWriterException: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause) {
        cause.printStackTrace()
    }
}

/**
 * HWP 문서 쓰기 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
internal class HWPWriteException: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause)
}

/**
 * HWP 문서 빌드 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
class HWPBuildException: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause)
}

/**
 * HWP 문서 붙혀넣기 예외를 나타내는 객체
 * @see [RuntimeException]
 *
 * @author accforaus
 */
class HWPCopyToExcention: RuntimeException {
    constructor(msg: String) : super(msg)
    constructor(msg: String, cause: Throwable) : super(msg, cause)
}