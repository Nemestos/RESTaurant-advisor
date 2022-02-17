<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Laravel\Passport\HasApiTokens;

class User extends Authenticatable
{
    use HasFactory, HasApiTokens;

    protected $connection = 'mongodb';
    protected $primaryKey = "login";
    protected $fillable = [
        'login',
        'email',
        'name',
        "password",
        'firstname',
        'age',
        'role'
    ];

    protected $collection = "users";


    public function getAuthPassword()
    {
        return $this->password;
    }

    public function getAuthIdentifier()
    {
        return $this->login;
    }

}
