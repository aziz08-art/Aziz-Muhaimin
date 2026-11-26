# 📚 Perpustakaan Mini - Aziz Muhaimin
**Nama:** Aziz Muhaimin
**Kelas:** X PPLG
**Mata Pelajaran:** KKA / PPL
**Bahasa:** Java

## Deskripsi
Aplikasi console untuk mengelola buku perpustakaan mini.
Menggunakan array, percabangan, perulangan, dan fungsi.

## Fitur
1. Tambah Buku
2. Tampilkan Buku
3. Ubah Buku
4. Hapus Buku (Tidak bisa hapus jika Dipinjam)
5. Pinjam Buku (Input Nama Peminjam)
6. Kembalikan Buku
7. Keluar

## Cara Menjalankan
1. Buka https://www.programiz.com/java-programming/online-compiler/
2. Copy paste file `Main.java`
3. Klik Run
4. Pilih menu 1-7

## Algoritma
1. Mulai program
2. Inisialisasi 10 buku awal dengan status Tersedia
3. Tampilkan Menu Utama 1-7 dan hitung Total / Tersedia / Dipinjam
4. Minta user pilih menu
5. Jika pilih 1 Tambah -> Input judul, penulis, tahun -> Simpan ke array
6. Jika pilih 2 Tampil -> Looping tampilkan semua buku
7. Jika pilih 3 Ubah -> Cari buku. Jika tidak ketemu tampilkan "Buku tidak ditemukan". Jika ketemu update data
8. Jika pilih 4 Hapus -> Cari buku. Jika tidak ketemu tampilkan error. Jika status Dipinjam tolak hapus. Jika Tersedia hapus
9. Jika pilih 5 Pinjam -> Cari buku. Jika Tersedia ubah jadi Dipinjam + simpan nama peminjam. Jika Dipinjam tampilkan "Sudah Dipinjam"
10. Jika pilih 6 Kembali -> Cari buku. Jika Dipinjam ubah jadi Tersedia. Jika Tersedia tampilkan "Tidak sedang Dipinjam"
11. Jika pilih 7 Keluar -> Tampilkan "Terima kasih" dan selesai
12. Jika pilihan tidak valid -> Tampilkan "Pilihan tidak valid" dan kembali ke menu
13. Selesai

## Pseudocode
