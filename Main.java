
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    static Scanner input = new Scanner(System.in);
    static ArrayList<Buku> daftarBuku = new ArrayList<>();
    static int nomorBerikutnya = 1;
    static final int KAPASITAS = 100;
    static final String FILE = "buku.txt";

    // CLASS BUKU
    static class Buku {
        int nomor;
        String judul, penulis, status;

        Buku(int nomor, String judul, String penulis, String status) {
            this.nomor = nomor;
            this.judul = judul;
            this.penulis = penulis;
            this.status = status;
        }
    }

    // SIMPAN DATA
    static void simpanData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
            for (Buku b : daftarBuku) {
                pw.println(b.nomor + "|" + b.judul + "|" +
                           b.penulis + "|" + b.status);
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data!");
        }
    }

    // MUAT DATA
    static void muatData() {
        File file = new File(FILE);

        if (!file.exists()) {
            isiDataAwal();
            simpanData();
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String baris;
            while ((baris = br.readLine()) != null) {
                String[] data = baris.split("\\|", 4);
                if (data.length == 4) {
                    int nomor = Integer.parseInt(data[0]);
                    daftarBuku.add(new Buku(nomor, data[1],
                                            data[2], data[3]));
                    nomorBerikutnya = Math.max(nomorBerikutnya, nomor + 1);
                }
            }
        } catch (Exception e) {
            System.out.println("Gagal membaca data!");
        }
    }

    // DATA AWAL
    static void isiDataAwal() {
        tambahAwal("Laskar Pelangi", "Andrea Hirata");
        tambahAwal("Bumi", "Tere Liye");
        tambahAwal("Bulan", "Tere Liye");
        tambahAwal("Negeri 5 Menara", "Ahmad Fuadi");
        tambahAwal("Dilan 1990", "Pidi Baiq");
        tambahAwal("Ronggeng Dukuh Paruk", "Ahmad Tohari");
        tambahAwal("Bumi Manusia", "Pramoedya Ananta Toer");
        tambahAwal("Hujan", "Tere Liye");
        tambahAwal("Perahu Kertas", "Dewi Lestari");
        tambahAwal("Sang Pemimpi", "Andrea Hirata");
    }

    static void tambahAwal(String judul, String penulis) {
        daftarBuku.add(new Buku(nomorBerikutnya++, judul,
                                penulis, "Tersedia"));
    }

    // CARI BUKU
    static Buku cariBuku() {
        System.out.print("Masukkan judul atau nomor buku: ");
        String kata = input.nextLine().trim();

        for (Buku b : daftarBuku) {
            if (kata.equalsIgnoreCase(b.judul) ||
                kata.equals(String.valueOf(b.nomor))) {
                tampilkanDetail(b);
                return b;
            }
        }

        System.out.println("Buku tidak ditemukan!");
        return null;
    }

    // DETAIL BUKU
    static void tampilkanDetail(Buku b) {
        System.out.println("Nomor   : " + b.nomor);
        System.out.println("Judul   : " + b.judul);
        System.out.println("Penulis : " + b.penulis);
        System.out.println("Status  : " + b.status);
    }

    // 1. TAMBAH
    static void tambahBuku() {
        if (daftarBuku.size() >= KAPASITAS) {
            System.out.println("Kapasitas penuh!");
            return;
        }

        System.out.print("Judul buku: ");
        String judul = input.nextLine().trim();

        System.out.print("Nama penulis: ");
        String penulis = input.nextLine().trim();

        if (judul.isEmpty() || penulis.isEmpty()) {
            System.out.println("Data tidak boleh kosong!");
            return;
        }

        daftarBuku.add(new Buku(nomorBerikutnya++, judul,
                                penulis, "Tersedia"));
        simpanData();
        System.out.println("Buku berhasil ditambahkan!");
    }

    // 2. TAMPILKAN
    static void tampilkanBuku() {
        System.out.println("\n=== DAFTAR BUKU ===");
        System.out.println("Total buku: " + daftarBuku.size());

        for (Buku b : daftarBuku) {
            System.out.println("-------------------");
            tampilkanDetail(b);
        }
    }

    // 3. UBAH
    static void ubahBuku() {
        Buku b = cariBuku();

        if (b != null) {
            System.out.print("Judul baru: ");
            String judul = input.nextLine().trim();

            System.out.print("Penulis baru: ");
            String penulis = input.nextLine().trim();

            if (judul.isEmpty() || penulis.isEmpty()) {
                System.out.println("Data tidak boleh kosong!");
                return;
            }

            b.judul = judul;
            b.penulis = penulis;
            simpanData();
            System.out.println("Buku berhasil diubah!");
        }
    }

    // 4. HAPUS
    static void hapusBuku() {
        Buku b = cariBuku();

        if (b != null) {
            if (b.status.equals("Dipinjam")) {
                System.out.println("Buku sedang dipinjam!");
            } else {
                daftarBuku.remove(b);
                simpanData();
                System.out.println("Buku berhasil dihapus!");
            }
        }
    }

    // 5. PINJAM
    static void pinjamBuku() {
        Buku b = cariBuku();

        if (b != null) {
            if (b.status.equals("Tersedia")) {
                b.status = "Dipinjam";
                simpanData();
                System.out.println("Peminjaman berhasil!");
            } else {
                System.out.println("Buku sedang dipinjam!");
            }
        }
    }

    // 6. KEMBALIKAN
    static void kembalikanBuku() {
        Buku b = cariBuku();

        if (b != null) {
            if (b.status.equals("Dipinjam")) {
                b.status = "Tersedia";
                simpanData();
                System.out.println("Buku berhasil dikembalikan!");
            } else {
                System.out.println("Buku tidak sedang dipinjam!");
            }
        }
    }

    // MENU UTAMA
    static void menuUtama() {
        while (true) {
            System.out.println("\n=== PERPUSTAKAAN MINI ===");
            System.out.println("Total Buku: " + daftarBuku.size());
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Buku");
            System.out.println("3. Ubah Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("5. Pinjam Buku");
            System.out.println("6. Kembalikan Buku");
            System.out.println("7. Cari Buku");
            System.out.println("8. Keluar");

            System.out.print("Pilih menu: ");
            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1": tambahBuku(); break;
                case "2": tampilkanBuku(); break;
                case "3": ubahBuku(); break;
                case "4": hapusBuku(); break;
                case "5": pinjamBuku(); break;
                case "6": kembalikanBuku(); break;
                case "7": cariBuku(); break;
                case "8":
                    simpanData();
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public static void main(String[] args) {
        muatData();
        menuUtama();
    }
}
