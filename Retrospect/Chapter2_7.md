# Recorder

<br />
<p align="center">
  
  <img width="300" height="450" src="https://user-images.githubusercontent.com/89181586/169694991-b4446781-84ad-4959-9789-ca9d24fe0a89.png">
  <img width="300" height="450" src="https://user-images.githubusercontent.com/89181586/169695017-3a836dfb-c50e-486f-9fe7-850a6b0a4d79.png">
</p>

## 강의에서 배운점
```
 - Subclass a View -> Context와 AttributeSet을 파라미터로 전달
 - Vector Asset으로 원하는 아이콘을 추가할 수 있다.
 - setAudioSource(MediaRecorder.AudioSource.MIC)로 마이크 접근
 - setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP) 레코딩 후 실제 파일로 저장할 때 사용
 - setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) Audio Recording 시 Audio Encoder 를 설정해 주는 함수 입니다
 - setOutputFile(프로퍼티) 파일저장
 - prepare() prepare 단계는 앞서 설정한 설정값들로 recording 을 준비하는 단계 입니다.
 - onSizeChanged를 사용하여 목소리의 크기에 따라 모양이 변화하는 방법을 배웠습니다.
```

<br />
<p align="center">
<img src="https://user-images.githubusercontent.com/89181586/169697857-7a2f83d2-481f-494d-a3b1-2172c3dd5eac.png">
</p>

##
https://developer.android.com/reference/android/media/MediaRecorder.html
