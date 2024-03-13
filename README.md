<details> 
<summary> Tutorial 5 </summary>

## JMeter Result
### `/all-student` Endpoint
- **Before** Optimization :

    ![JMeter All Student Before](/image/jmeterbefore1.jpg)


- **After** Optimization :

    ![JMeter All Student After](/image/jmeterafter1.jpg)


- **Comparison** :

  | Before   | After  | Improvement |
    |----------|--------|-------------|
  | 8,587 ms | 944 ms | 89%         |


### `/all-student-name` Endpoint
- **Before** Optimization :

    ![JMeter All Student Name Before](/image/jmeterbefore2.jpg)


- **After** Optimization :

    ![JMeter All Student Name After](/image/jmeterafter2.jpg)


- **Comparison** :
    | Before   | After  | Improvement |
    |----------|--------|-------------|
    | 1,396 ms | 345 ms | 75.29%      |

### `/highest-gpa` Endpoint
- **Before** Optimization :

    ![JMeter Highest GPA Before](/image/jmeterbefore3.jpg)


- **After** Optimization :

    ![JMeter Highest GPA After](/image/jmeterafter3.jpg)


- **Comparison** :

    | Before | After  | Improvement |
    |--------|--------|-------------|
    | 685 ms | 390 ms | 43.1%       |

### Combined JMeter Result using `cmd` :
- **Before** Optimization :

    ![JMeter Command Before](/image/jmetercmdbefore.jpg)


- **After** Optimization :

    ![JMeter Command After](/image/jmetercmdafter.jpg)



## Reflection
### Perbedaan antara JMeter Tests dan Profiling dengan Profiler IntelliJ dalam Optimization

Secara tujuan dan kegunaan umum, keduanya memiliki perbedaan sebagai berikut:
- **Testing with Jmeter** : 
Jmeter digunakan untuk menguji aplikasi terhadap berbagai macam intensitas _load_ atau beban kerja dari user terhadap aplikasi, seperti mensimulasikan seberapa mampu aplikasi meng-_handle_ akses dari banyak pengguna dalam waktu yang bersamaan. Jmeter dapat kita gunakan untuk menguji performa keseluruhan aplikasi, termasuk dengan _database_ dan _server response_. 
- **Profiling with Profiler IntelliJ** :
Profiler IntelliJ digunakan untuk menganalisis performa aplikasi, seperti menemukan _bottleneck_ atau bagian dari kode aplikasi yang memakan banyak _resource_ sehingga memperlambat aplikasi. Profiling juga dapat digunakan untuk menemukan _memory leak_ atau bagian kode dari aplikasi yang memakan banyak _memory_ sehingga memperlambat aplikasi. Profiler dapat digunakan untuk mencari isu performa dan meningkatkan efisiensi pada kode.

### Bagaimana Proses Profiling Membantu dalam Mencari Kelemahan Aplikasi
Proses _profiling_ membantu kita untuk melihat bagian kode mana yang membuat aplikasi menjadi lambat dan berat dengan informasi yang diberikan seperti _CPU time, memory allocation, execution time_. Sehingga, akan lebih mudah untuk menentukan bagian kode mana yang lemah dan perlu dioptimasi.

### Apakah Intellij Profiler Efektif?
Dari pengalaman saya menggunakan IntelliJ Profiler, saya merasa IntelliJ Profiler cukup efektif dalam membantu saya menemukan _bottleneck_ pada aplikasi. Dengan informasi yang diberikan, saya dapat menemukan bagian _bottleneck_ yang harus saya perbaiki untuk menignkatkan performa aplikasi. Bentuk penyajian data hasil yang beragam seperti _flame graph, method list, dan method tree_ juga efektif dalam menyajikan data yang mudah kita lihat informasinya.

### Kendala dalam Melakukan Performance Testing dan Profiling
Sebenarnya secara prosedur dan alur testing/profiling untuk optimisasi, tidak ada kendala berarti yang saya alami karena panduan yang ada pada modul sudah cukup jelas. Namun, saya tetap mengalami kendala-kendala kecil seperti:
- Memahami cara menilai bottleneck dari hasil profiling (terutama _graph_) karena ini merupakan kali pertama saya melakukan hal serupa. Dapat diatasi dengan melihat hasil dengan view lain seperti _method list_.
- Memahami alur method/kode yang perlu saya optimisasi karena kode awal sudah disediakan, bukan membuat sendiri. Dapat dengan mudah diatasi dengan melakukan tracing pemanggilan method ke _object_ atau _repository_ bersangkutan.

### Manfaat Menggunakan IntelliJ Profiler untuk Profiling
Seperti yang sudah dijelaskan sebelumnya, IntelliJ Profiler sangat membantu dalam menemukan _bottleneck_ pada aplikasi. Dengan informasi yang diberikan, kita dapat menemukan bagian kode mana yang perlu dioptimasi untuk meningkatkan performa aplikasi. Dengan adanya profiler ini, saya dapat melakukan semua proses untuk optimisasi hanya dalam satu aplikasi yaitu _IDE_ tanpa perlu menggunakan _tools_ lain.

### Apa yang Perlu Dilakukan jika Hasil pada IntelliJ Profiler dan JMeter Tidak Konsisten?
Dalam mengerjakan tutorial 5 ini, saya tidak mengalami adanya hasil yang tidak konsisten antara IntelliJ Profiler dan JMeter. Namun, jika hasilnya tidak konsisten, kita perlu melakukan pengecekan ulang terhadap _test case_ yang digunakan, _load_ yang diberikan, dan _environment_ yang digunakan. Kita juga perlu memastikan kondisi awal dan _metric_ yang digunakan pada kedua aplikasi juga sama.

### Optimisasi yang Diimplementasi pada Kode
- **Mengurangi Pemanggilan _Database_ dan Menggunakan Hashmap untuk Efisiensi Pencarian pada `getAllStudent()`:**

    Dalam kode yang sudah teroptimisasi, saya melakukan pemanggilan ke _database_ menggunakan method `findAll` agar tidak diperlukan pemanggilan secara berulang seperti sebelumnya, dan untuk meningkatkan efisiensi pencarian `Student` berdasarkan id, saya menggunakan ADT `HashMap`.


- **Mengurangi Penggunaan Memori untuk Pembuatan Objek Baru dan Penggunaan `Stream` pada `joinStudentsName()`:**

    Dalam kode yang sudah teroptimisasi, saya mengurangi penggunaan memori untuk pembuatan objek baru pada konkatens string dengan menggunakan `StringBuilder`. Penggunaan `Stream` juga saya gunakan untuk menggantikan `for-loop` agar menghindari adanya _mutable state object_ yang dapat mempengaruhi performa aplikasi.


- **Menggunakan `Stream` dan `Comparator` pada `findStudentWithHighestGPA()`:**

    Sama seperti sebelumnya, saya mereplace pengunaan `for-loop` dengan `Stream` dan `Comparator` untuk mencari `Student` dengan nilai GPA tertinggi. Pendekatan ini lebih baik performanya dibandingkan menggunakan perbandingan nilai secara `for-loop` dan menghindari juga adanya _mutable object_.

</details>
