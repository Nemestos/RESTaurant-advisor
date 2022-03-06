<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Restaurant extends Model
{
    use HasFactory;

    protected $fillable = [
        'name',
        'description',
        'grade',
        'localization',
        'phone_number',
        'website',
        'hours'
    ];

    public static function getMean($id)
    {
        $restaurant = Restaurant::find($id);
        if ($restaurant == null) {
            return 0;
        }
        $reviews = $restaurant->reviews;
        if (!$reviews->count()) {
            return $restaurant->grade;
        }
        $sum = 0;
        $length = 0;
        $reviews->each(function (Review $review) use (&$length, &$sum) {
            $sum += $review->grade;
            $length += 1;
        });
        return round($sum / $length, 2);
    }

    public function menus()
    {
        return $this->hasMany(Menu::class, "restaurant_id");
    }

    public function reviews()
    {
        return $this->hasMany(Review::class, "restaurant_id");
    }

    protected $collection = "restaurants";
}
