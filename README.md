## HWP File Format Library

<p align="center" style="padding: 45px;">
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

* 빈 문서 (Empty Document)
    * 한글 프로그램을 실행시키면 나오는 빈 문서를 제어할 수 있습니다.
    * 기본 생성자를 이용 합니다.
```kotlin
val hwp: HWPDocument = HWPDocument() // basic constructor makes empty document

// do someing...
```


* 한글문서(HWP) 이어 붙히기
```kotlin
val original_hwp: HWPDocument = HWPDocument('...') // will be appended

val target_hwp: HWPDocument = HWPDocument('...') // target HWP Document

target_hwp + original_hwp
//or
target_hwp = target_hwp + original_hwp
//or
target_hwp = target_hwp.plus(original_hwp)

target_hwp.addParagraph(some_paragraph, original_hwp) // add paragraph in target_hwp

target_hwp.addParagraphs(some_paragraphs, original_hwp) // add paragraph list in target_hwp

// do something
```

## Credits for [hwplib](https://github.com/neolord0/hwplib)
A huge thanks to the author of hwplib for most of the work on libhwp.

## License
[Apache](LICENSE)
© [accforaus](https://github.com/accforaus)