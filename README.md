## HWP File Format Library

<p align="center">
    <img src="/img/hancomImg.png">
</p>

> 본 라이브러리는 한글과 컴퓨터의 한글 문서 파일(.hwp) 공개 문서를 참고하여 개발하였습니다.

## Features

* 한글문서(HWP) 읽기 (read)
```kotlin
val hwpLocal: HWPDocument = HWPDocument("local_file_paths") // 로컬 파일 읽기

val hwpUrl: HWPDocument = HWPDocuemnt("some_url_path") // URL 경로로 부터 읽기

// do something...
```

* 한글문서(HWP) 쓰기 (write)
```kotlin
val hwp: HWPDocument = HWPDocument('...') // 한글 문서 읽기

hwp.write('write_path') // 한글 문서 지정된 경로로 쓰기

// do something...
```

* 한글문서(HWP) 복사하기 (copy)
```kotlin
val hwp: HWPDocument = HWPDocument('...') // 한글 문서 읽기

val copied: HWPDocument = hwp.copy() // 한글 문서 복사하기

// do something...
```

## Credits for [hwplib](https://github.com/neolord0/hwplib)
A huge thanks to the author of hwplib for most of the work on libhwp.

## License
[Apache](LICENSE)
© [accforaus](https://github.com/accforaus)