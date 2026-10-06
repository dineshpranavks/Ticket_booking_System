package model;

public class User
{
    private int userId;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;

    public User(int userId, String name, String email, String phone, String password, String role)
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhone()
    {
        return phone;
    }

    public String getPassword()
    {
        return password;
    }

    public String getRole()
    {
        return role;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void displayUser()
    {
        System.out.println("User ID : " + userId);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Phone   : " + phone);
    }
}