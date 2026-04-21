# SauceDemo Automation Testing - Katalon Studio

Repositori ini berisi skrip automasi pengujian untuk website [SauceDemo](https://www.saucedemo.com/) menggunakan **Katalon Studio**. Pengujian mencakup alur utama mulai dari Login, Sorting Produk, hingga proses Checkout.

## 📌 Informasi Proyek
- **Alat**: Katalon Studio (Enterprise/Free)
- **Bahasa**: Groovy
- **Browser**: Google Chrome (Mode: Incognito & Headless)
- **Metode**: Data-Driven Testing (DDT) menggunakan file Excel

---

## 📄 Dokumentasi SIT (System Integration Test)
Silakan akses dokumen pengujian lengkap melalui link di bawah ini:

> 🔗 [**Link Dokumen SIT - Klik di Sini**](https://docs.google.com/spreadsheets/d/1O7CdGM-GytKbUywjDaxfxjf3VnumZxUe/edit?usp=drive_link&ouid=102556497230646308513&rtpof=true&sd=true)

---

## 🧪 Skenario Pengujian
Proyek ini mencakup beberapa modul utama:

### 1. Modul Login (`TC_01_Login`)
- Login dengan user valid (`standard_user`).
- Login dengan user yang diblokir (`locked_out_user`).
- Login dengan kredensial salah.

### 2. Modul Inventory (`TC_02_SortProduct`)
- Sorting produk berdasarkan Nama (A-Z dan Z-A).
- Sorting produk berdasarkan Harga (Low to High dan High to Low).
- Verifikasi kesesuaian produk pertama setelah disortir.

### 3. Modul Checkout (`TC_03_Checkout`)
- Menambahkan produk ke keranjang via JavaScript Click.
- Pengisian form informasi (First Name, Last Name, Postal Code).
- **Positive Test**: Checkout sampai halaman Overview.
- **Negative Test**: Validasi pesan error jika ada field yang dikosongkan.

---

## 📊 Struktur Data (Excel)
Data pengujian dikelola melalui file Excel dengan header berikut:
- `tc_id`: Kode unik test case (Contoh: CCO-P-01, CCO-N-01).
- `scenario`: Deskripsi singkat skenario.
- `first_name` / `last_name` / `postal_code`: Data input form.
- `expected_result`: Ekspektasi hasil (Nama Produk, Judul Halaman, atau Pesan Error).

---

## ⚙️ Konfigurasi Lingkungan
Untuk menghindari gangguan notifikasi Chrome ("Change Password"), pastikan pengaturan berikut telah diterapkan:

1. Pergi ke **Project > Settings > Desired Capabilities > Web UI > Chrome**.
2. Tambahkan variabel `args` bertipe **List**.
3. Masukkan nilai berikut:
   - `--incognito`
   - `--disable-notifications`
   - `--disable-infobars`
   - `--disable-save-password-bubble`

---

## 🚀 Cara Menjalankan Tes
1. Clone repositori ini.
2. Buka proyek di **Katalon Studio**.
3. Pastikan file Excel sudah terhubung di bagian **Data Files**.
4. Buka **Test Suites > TS_SauceDemo**.
5. Klik **Run** menggunakan browser **Chrome**.

---

## 📝 Catatan Penting
- Skrip menggunakan **JavaScript Click** (`executeJavaScript`) untuk menangani elemen yang sulit diklik secara standar.
- Verifikasi hasil menggunakan **Variable Binding** antara Excel dan Test Case Variables.