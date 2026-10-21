# 🧮 Kalkulator Saintifik Profesional Java (Berorientasikan Objek)

Projek ini adalah sebuah aplikasi Kalkulator Saintifik yang dibangunkan sepenuhnya menggunakan **Java Swing**. Ia menampilkan paparan matematik yang diformat dengan HTML, kod warna berstruktur (*syntax highlighting*), dan menonjolkan penggunaan konsep **Pengaturcaraan Berorientasikan Objek (OOP)**.

Matlamat utama repositori ini adalah sebagai **bahan perkongsian rujukan pendidikan**. Jika anda baru belajar Java, penerangan di bawah akan membantu anda memahami bagaimana konsep teori OOP diterjemahkan ke dalam kod sebenar, serta perbezaannya dengan bahasa C++.

---

## 🕰️ Sejarah Pembangunan & Perkakasan (Nostalgia)

Satu fakta menarik tentang projek ini ialah evolusi masa mula ia dibina adalah pada perkakasannya. Kod asal projek ini mula dibina pada **Oktober 2002** dan telah disimpan direpositori peribadi, setelah melalui evolusi pengkomputeran selama lebih dua dekad sebelum penulis menambahbaik untuk dikemas kini ke teknologi moden pada **20 Ogos 2026**.

**🖥️ Perkakasan Asal (Oktober 2002):**
* **Pemproses (CPU):** AMD K5-PR150
* **Papan Induk:** Asus Socket 7
* **Memori (RAM):** 32 MB SDRAM PC66/PC100
* **Storan:** Harddisk 1.2 GB
* **Kad Grafik:** 3dfx Voodoo Banshee PCI 16MB
* **Pemacu Optik & Cakera:** CD-RW Drive (8x) & 3.5-inch Floppy Disk Drive (1.44 MB)
* **Audio:** Creative Sound Blaster 16 PCI
* **Sistem Operasi:** Linux Slackware 8.0 (Kernel Linux 2.4.19)


**💻 Perkakasan Kemaskini Terkini (20 Ogos 2026):**
* **Mesin:** MacBook Pro 14-inch
* **Pemproses (CPU/Cip):** Apple M5 Pro
* **Memori (RAM):** 24 GB Unified Memory
* **Storan:** 1 TB HDD / SSD
* **Sistem Operasi:** MacOS Golden Gate 27.0.1

---

## 📚 1. Apa itu Pengaturcaraan Berorientasikan Objek (OOP) dalam Java?

OOP adalah paradigma pengaturcaraan di mana kita menstrukturkan perisian sebagai himpunan "objek". Setiap objek mempunyai **keadaan / data** (pemboleh ubah) dan **tingkah laku** (kaedah/fungsi).

Dalam projek kalkulator ini, kita mengaplikasikan beberapa konsep utama OOP:

* **Class & Object:**
  Fail kita bermula dengan `public class ScientificCalculator`. Kelas ini adalah "rangka tindakan" (blueprint). Apabila kita menjalankan `new ScientificCalculator()` di dalam fungsi `main`, kita sedang mencipta satu **Objek** (sebuah tetingkap kalkulator fizikal di skrin).

* **Inheritance - `extends`:**
  Kod kita menggunakan `extends JFrame`. Ini bermaksud class kalkulator kita "mewarisi" semua sifat tetingkap desktop sedia ada dalam Java (seperti butang pangkah, fungsi saiz semula, dan bar tajuk) tanpa perlu kita kodkan ciri-ciri asas tersebut dari sifar. Kalkulator kita **adalah sebuah** tetingkap UI.

* **Interface - `implements`:**
  Kita menggunakan `implements ActionListener`. Ini memaksa class kita untuk memiliki tingkah laku tertentu—iaitu keupayaan untuk "mendengar" (listen) dan bertindak balas apabila pengguna menekan butang kalkulator (melalui fungsi `actionPerformed`).

* **Encapsulation:**
  Kod memerhatikan pemboleh ubah seperti `private double num1` atau `private String operator`. Penggunaan kata kunci `private` menyembunyikan data ini daripada diakses atau diubah secara sebarangan oleh class luar. Ia hanya boleh diubah secara terkawal melalui logik dalaman kalkulator kita.

## ⚔️ 2. Perbezaan Utama: Java OOP vs C++ OOP

Jika anda datang dari latar belakang C++ seperti mana penulis dahulu atau mungkin sedang membandingkan kedua-dua bahasa ini, berikut adalah perbezaan teras yang perlu anda tahu:

| Ciri-ciri | Java | C++ | 
| ----- | ----- | ----- | 
| **Pengurusan Memori** | **Automatik:** Java mempunyai *Garbage Collector* yang akan membersihkan memori objek yang tidak lagi digunakan secara automatik. Tiada konsep rujukan memori (Pointers) secara langsung. | **Manual:** Pengaturcara mesti memadam memori sendiri menggunakan `delete`. Mempunyai akses penuh kepada Pointers. | 
| **Pewarisan Berbilang (*Multiple Inheritance*)** | **Tidak Dibenarkan:** Satu kelas hanya boleh `extends` satu kelas induk (cth: hanya `JFrame`). Namun, ia menyokong pelaksanaan pelbagai *Interface* (cth: `implements ActionListener, KeyListener`). | **Dibenarkan:** Satu kelas boleh mewarisi ciri dari pelbagai kelas induk secara serentak (boleh menyebabkan konflik *Diamond Problem*). | 
| **Kebebasan Platform** | **Write Once, Run Anywhere (WORA):** Kod Java dikompil kepada *Bytecode* yang dijalankan oleh *Java Virtual Machine (JVM)*. Kalkulator ini boleh dijalankan di Windows, Mac, atau Linux tanpa ubahsuai. | **Bergantung Platform:** Kod C++ dikompil terus kepada bahasa mesin (*native machine code*). Anda perlu *compile* semula kod untuk OS yang berbeza. | 

## 🛠️ 3. Bedah Siasat Kod (Bagaimana Kalkulator Ini Berfungsi)

Berikut adalah pecahan komponen utama di dalam kod `ScientificCalculator.java` supaya anda mudah memahaminya:

### A. Penyediaan UI (Dalam *Constructor*)

Apabila aplikasi dimulakan, fungsi pembina (`public ScientificCalculator()`) akan berjalan:

1. **Penyediaan Tetingkap:** `setSize(420, 600)` menetapkan dimensi kalkulator.

2. **Enjin Skrin HTML:** Kita menggunakan `JTextPane` dengan `setContentType("text/html")`. Ini adalah rahsia bagaimana paparan kalkulator kita boleh mempunyai teks berwarna (warna cyan untuk nombor, oren untuk simbol operasi) seolah-olah melihat buku teks matematik profesional.

3. **Gelung Butang (Button Loop):** Daripada menulis butang satu persatu, kita menggunakan tatasusunan (*array*) untuk senarai butang (`"sin", "cos", "7", "8"...`) dan menggunakan gelung `for` untuk menjananya secara automatik dan menyusunnya dalam bentuk Grid (`GridLayout`).

### B. Penggayaan Dinamik (`createStyledButton`)

Fungsi ini menyemak jenis teks pada butang. Jika ia nombor `0-9`, berwarna kelabu. Jika `=`, warnanya hijau. Ia menggunakan padanan corak (*Regex*) seperti `text.matches("[0-9]|\\.")` untuk mengkategorikan butang secara pintar.

### C. Logik Otak Kalkulator (`actionPerformed`)

Ini adalah pusat kawalan (otak) projek ini. Apabila pengguna menekan butang, parameter `ActionEvent e` membawa maklumat butang mana yang ditekan:

* Jika **nombor** ditekan: Ia disambungkan ke dalam memori `currentInput`.

* Jika **Fungsi Matematik (seperti `sin`, `√`)**: Ia memanggil `executeScientificFunction()`, menukar input kepada radian, mengira nilai segera menggunakan kelas utiliti `Math`, dan memaparkan formula terperinci.

* Jika **Operator Binari (seperti `+`, `×`)**: Ia menyimpan nombor pertama ke dalam `num1`, menyimpan simbol ke `operator`, dan bersedia untuk nombor kedua.

* Jika **Sama Dengan (`=`)**: Fungsi `calculateBinaryResult()` dipanggil. Ia mengambil `num1`, nombor terkini sebagai `num2`, dan melaksanakan matematik berdasarkan jenis `operator`, kemudian merender hasil jawapan berwarna hijau.

### D. Menjalankan Aplikasi Secara Selamat (`main`)

Fungsi `main` menggunakan `SwingUtilities.invokeLater()`. Dalam pembangunan perisian UI (*multithreading*), ini adalah amalan terbaik bagi memastikan semua komponen grafik dilukis di atas "laluan" (*thread*) yang dikhaskan, mengelakkan aplikasi daripada tersekat (crash).

## 🚀 Cara Menjalankan Projek Ini

1. Muat turun atau *Clone* repositori ini: `git clone https://github.com/username/HelloWorldJava.git` *(Gantikan dengan pautan sebenar anda)*
2. Buka mana-mana IDE Java (Eclipse, IntelliJ IDEA, atau VS Code).
3. Jalankan fail `ScientificCalculator.java` secara native. (Tiada library/keperbergantungan luaran diperlukan).