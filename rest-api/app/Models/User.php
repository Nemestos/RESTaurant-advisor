<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Jenssegers\Mongodb\Eloquent\Model;
use Jenssegers\Mongodb\Eloquent\Model as Eloquent;
use Jenssegers\Mongodb\Auth\User as Authenticatable;
use Laravel\Sanctum\HasApiTokens;
use Tymon\JWTAuth\Contracts\JWTSubject;

class User extends Authenticatable implements JWTSubject
{
    use HasFactory;
    protected $connection = 'mongodb';

    protected $fillable =[
        'login',
        'email',
        'name',
        "password",
        'firstname',
        'age',
        'role'
    ];

    protected $collection="users";

    public function getJWTIdentifier()
    {
        $this->getKey();
    }

    public function getJWTCustomClaims(): array
    {
        return [];
    }
    public function getAuthPassword()
    {
        return $this->password;
    }
    public function getAuthIdentifier()
    {
        return $this->login;
    }

}
