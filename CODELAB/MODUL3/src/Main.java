// Kelas dasar untuk karakter dalam game
class KarakterGame {
    private String nama; // Menyimpan nama karakter
    private int kesehatan; // Menyimpan nilai kesehatan karakter

    // Konstruktor untuk inisialisasi nama dan kesehatan karakter
    public KarakterGame(String nama, int kesehatan) {
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    // Getter untuk mengambil nama karakter
    public String getNama() {
        return nama;
    }

    // Getter untuk mengambil nilai kesehatan karakter
    public int getKesehatan() {
        return kesehatan;
    }

    // Setter untuk mengubah nilai kesehatan karakter
    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    // Metode untuk menyerang karakter lain (akan di-override oleh subclass)
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama());
    }
}

// Subkelas Pahlawan yang merupakan turunan dari KarakterGame
class Pahlawan extends KarakterGame {
    // Konstruktor untuk membuat pahlawan dengan nama dan kesehatan
    public Pahlawan(String nama, int kesehatan) {
        super(nama, kesehatan); // Memanggil konstruktor superclass
    }

    // Override metode serang untuk memberikan efek khusus
    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan pedang! ");
        target.setKesehatan(target.getKesehatan() - 20); // Mengurangi kesehatan target sebesar 20
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}

// Subkelas Musuh yang merupakan turunan dari KarakterGame
class Musuh extends KarakterGame {
    // Konstruktor untuk membuat musuh dengan nama dan kesehatan
    public Musuh(String nama, int kesehatan) {
        super(nama, kesehatan); // Memanggil konstruktor superclass
    }

    // Override metode serang untuk memberikan efek khusus
    @Override
    public void serang(KarakterGame target) {
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan sihir!");
        target.setKesehatan(target.getKesehatan() - 15); // Mengurangi kesehatan target sebesar 15
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}

// Kelas utama untuk menjalankan program
class c1m3 {
    public static void main(String[] args) {
        // Membuat objek pahlawan dengan nama "Brimstone" dan kesehatan 150
        Pahlawan brimstone = new Pahlawan("Brimstone", 150);
        // Membuat objek musuh dengan nama "Viper" dan kesehatan 200
        Musuh viper = new Musuh("Viper", 200);

        // Menampilkan status awal kesehatan masing-masing karakter
        System.out.println("Status awal:");
        System.out.println(brimstone.getNama() + " memiliki kesehatan: " + brimstone.getKesehatan());
        System.out.println(viper.getNama() + " memiliki kesehatan: " + viper.getKesehatan());

        // Simulasi pertarungan
        brimstone.serang(viper); // Pahlawan menyerang musuh
        viper.serang(brimstone); // Musuh menyerang balik
    }
}
