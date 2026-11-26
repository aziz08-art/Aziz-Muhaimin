import java.util.Scanner;

class Buku {
    String judul; String penulis; int tahun; String status; String peminjam;
    Buku(String judul, String penulis, int tahun) {
        this.judul = judul; this.penulis = penulis; this.tahun = tahun;
        this.status = "Tersedia"; this.peminjam = "-";
    }
}

public class Main {
    static Buku[] daftarBuku = new Buku[20];
    static int jumlahBuku = 0;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // 10 BUKU AWAL - WAJIB MIN 10
        tambahBukuAwal("Laskar Pelangi", "Andrea Hirata", 2005);
        tambahBukuAwal("Bumi", "Tere Liye", 2014);
        tambahBukuAwal("Dilan 1990", "Pidi Baiq", 2014);
        tambahBukuAwal("Negeri 5 Menara", "Ahmad Fuadi", 2009);
        tambahBukuAwal("Ayat-Ayat Cinta", "Habiburrahman El Shirazy", 2004);
        tambahBukuAwal("Perahu Kertas", "Dee Lestari", 2009);
        tambahBukuAwal("Sang Pemimpi", "Andrea Hirata", 2006);
        tambahBukuAwal("5 CM", "Donny Dhirgantoro", 2007);
        tambahBukuAwal("Harry Potter", "J.K. Rowling", 1997);
        tambahBukuAwal("Atomic Habits", "James Clear", 2018);

        int pilihan;
        do {
            tampilkanMenu();
            System.out.print("Pilih menu (1-7): ");
            if (!input.hasNextInt()) {
                System.out.println("Pilihan harus angka!");
                input.nextLine();
                pilihan = 0;
                continue;
            }
            pilihan = input.nextInt(); input.nextLine();
            switch (pilihan) {
                case 1: tambahBuku(); break;
                case 2: tampilkanBuku(); break;
                case 3: ubahBuku(); break;
                case 4: hapusBuku(); break;
                case 5: pinjamBuku(); break;
                case 6: kembalikanBuku(); break;
                case 7: System.out.println("Terima kasih!"); break;
                default: System.out.println("Pilihan tidak valid!");
            }
            System.out.println();
        } while (pilihan != 7);
    }

    static void tampilkanMenu() {
        int tersedia = 0;
        for (int i = 0; i < jumlahBuku; i++) if (daftarBuku[i].status.equals("Tersedia")) tersedia++;
        System.out.println("=== PERPUSTAKAAN MINI ===");
        System.out.println("Total Buku: " + jumlahBuku + " | Tersedia: " + tersedia + " | Dipinjam: " + (jumlahBuku - tersedia));
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tampilkan Buku");
        System.out.println("3. Ubah Buku");
        System.out.println("4. Hapus Buku");
        System.out.println("5. Pinjam Buku");
        System.out.println("6. Kembalikan Buku");
        System.out.println("7. Keluar");
    }

    static void tambahBukuAwal(String judul, String penulis, int tahun) {
        daftarBuku[jumlahBuku] = new Buku(judul, penulis, tahun); jumlahBuku++;
    }

    static void tambahBuku() {
        System.out.print("Mau tambah berapa buku? ");
        int n = input.nextInt(); input.nextLine();
        for (int k = 0; k < n; k++) {
            if (jumlahBuku >= 20) { System.out.println("Penuh!"); return; }
            System.out.println("--- Buku ke-" + (k+1) + " ---");
            System.out.print("Judul: "); String judul = input.nextLine();
            System.out.print("Penulis: "); String penulis = input.nextLine();
            System.out.print("Tahun: ");
            int tahun;
            try { tahun = Integer.parseInt(input.nextLine()); }
            catch (Exception e) { System.out.println("Tahun harus angka, pakai 2024"); tahun = 2024; }
            daftarBuku[jumlahBuku] = new Buku(judul, penulis, tahun); jumlahBuku++;
            System.out.println("Berhasil ditambah!");
        }
    }

    static void tampilkanBuku() {
        if (jumlahBuku == 0) { System.out.println("Belum ada buku."); return; }
        int tersedia = 0;
        for (int i = 0; i < jumlahBuku; i++) if (daftarBuku[i].status.equals("Tersedia")) tersedia++;
        System.out.println("--- DAFTAR BUKU ---");
        System.out.println("Total: " + jumlahBuku + " buku (Tersedia: " + tersedia + ", Dipinjam: " + (jumlahBuku - tersedia) + ")");
        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].status.equals("Dipinjam"))
                System.out.println((i+1) + ". " + daftarBuku[i].judul + " | " + daftarBuku[i].penulis + " | " + daftarBuku[i].tahun + " | Dipinjam oleh: " + daftarBuku[i].peminjam);
            else
                System.out.println((i+1) + ". " + daftarBuku[i].judul + " | " + daftarBuku[i].penulis + " | " + daftarBuku[i].tahun + " | Tersedia");
        }
    }

    static void ubahBuku() {
        tampilkanBuku();
        System.out.print("Nomor buku yang diubah: "); int idx = input.nextInt() - 1; input.nextLine();
        if (idx < 0 || idx >= jumlahBuku) { System.out.println("Buku tidak ditemukan! Nomor tidak valid!"); return; }
        System.out.println("Data lama: " + daftarBuku[idx].judul + " - " + daftarBuku[idx].penulis + " (" + daftarBuku[idx].tahun + ")");
        System.out.print("Judul baru (Enter jika tidak diubah): "); String j = input.nextLine();
        if (!j.isEmpty()) daftarBuku[idx].judul = j;
        System.out.print("Penulis baru (Enter jika tidak diubah): "); String p = input.nextLine();
        if (!p.isEmpty()) daftarBuku[idx].penulis = p;
        System.out.print("Tahun baru (Enter jika tidak diubah): "); String t = input.nextLine();
        if (!t.isEmpty()) {
            try { daftarBuku[idx].tahun = Integer.parseInt(t); }
            catch (Exception e) { System.out.println("Tahun tidak valid, tidak diubah."); }
        }
        System.out.println("Buku berhasil diubah!");
    }

    static void hapusBuku() {
        tampilkanBuku();
        System.out.print("Nomor buku yang dihapus: "); int idx = input.nextInt() - 1; input.nextLine();
        if (idx < 0 || idx >= jumlahBuku) { System.out.println("Buku tidak ditemukan! Nomor tidak valid!"); return; }
        if (daftarBuku[idx].status.equals("Dipinjam")) { System.out.println("GAGAL! Dipinjam oleh " + daftarBuku[idx].peminjam + ", tidak bisa dihapus!"); return; }
        for (int i = idx; i < jumlahBuku - 1; i++) daftarBuku[i] = daftarBuku[i+1];
        jumlahBuku--; System.out.println("Buku berhasil dihapus!");
    }

    static void pinjamBuku() {
        tampilkanBuku();
        System.out.print("Nomor buku yang dipinjam: "); int idx = input.nextInt() - 1; input.nextLine();
        if (idx < 0 || idx >= jumlahBuku) { System.out.println("Buku tidak ditemukan! Nomor tidak valid!"); return; }
        if (daftarBuku[idx].status.equals("Tersedia")) {
            System.out.print("Nama peminjam: "); String nama = input.nextLine();
            if (nama.isEmpty()) { System.out.println("Nama peminjam tidak boleh kosong!"); return; }
            daftarBuku[idx].status = "Dipinjam"; daftarBuku[idx].peminjam = nama;
            System.out.println("Berhasil meminjam: " + daftarBuku[idx].judul + " oleh " + nama);
        } else System.out.println("GAGAL! Sudah Dipinjam oleh " + daftarBuku[idx].peminjam);
    }

    static void kembalikanBuku() {
        tampilkanBuku();
        System.out.print("Nomor buku yang dikembalikan: "); int idx = input.nextInt() - 1; input.nextLine();
        if (idx < 0 || idx >= jumlahBuku) { System.out.println("Buku tidak ditemukan! Nomor tidak valid!"); return; }
        if (daftarBuku[idx].status.equals("Dipinjam")) {
            System.out.println("Berhasil dikembalikan: " + daftarBuku[idx].judul + " dari " + daftarBuku[idx].peminjam);
            daftarBuku[idx].status = "Tersedia"; daftarBuku[idx].peminjam = "-";
        } else System.out.println("GAGAL! Buku tidak sedang Dipinjam!");
    }
                                       }
