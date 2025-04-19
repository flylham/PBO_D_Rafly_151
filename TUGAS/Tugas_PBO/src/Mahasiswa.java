public class Mahasiswa extends user {

    public Mahasiswa(String nama, String nim) {
        super(nama, nim);
    }
//overriding
    @Override
    public boolean login(String inputNama, String inputNim) {
        return inputNama.equals(getNama()) && inputNim.equals(getNim());
    }
//overridding
    @Override
    public void displayInfo() {
        System.out.println("Login Mahasiswa berhasil!");
        super.displayInfo();
    }
}
