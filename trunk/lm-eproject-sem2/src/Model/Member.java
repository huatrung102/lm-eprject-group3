/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Administrator PC
 */
public class Member {
    public String Mem_Id;
    public String Mem_FirstName;
    public String Mem_LastName;    
    public String Mem_Phone;
    public String Mem_Address;
    public String Mem_Email;
    public boolean Mem_Status;
    public String Mem_CreateDate;
    public String Mem_ImageFile;
    public boolean Mem_isDelete;
    
    public Member(){
    }
    public static Member getTestMember(){
        Member mem = new Member();
        mem.Mem_Address = "Address ABC";
        mem.Mem_CreateDate = "30/12/2014";
        mem.Mem_Email = "huatrung102@gmail.com";
        mem.Mem_FirstName = "Hua Tran";
        mem.Mem_LastName = "Huu Trung";
        mem.Mem_ImageFile = "/imgMem/anh (11).jpg";
        mem.Mem_Phone = "0934399664";
        mem.Mem_Status = true;
        mem.Mem_isDelete = false;
        return mem;
    }
}
