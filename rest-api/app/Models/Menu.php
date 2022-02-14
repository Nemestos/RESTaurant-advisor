<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Jenssegers\Mongodb\Eloquent\Model;

class Menu extends Model
{
    use HasFactory;
    protected $fillable = [
        "name",
        "description",
        "price"
    ];
    protected $collection = "menus";
}
