import ChopperMAN as ch
from os import path
import pygame

class Player(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(path.join(ch.img_dir,'Pulica1.png')).convert_alpha()
        self.image = pygame.transform.scale(self.image,(120,120))
        
        self.image.set_colorkey(ch.BLACK)
        
        self.rect = self.image.get_rect()
        self.rect.right = ch.WIDTH/2 - 30
        self.rect.y = ch.HEIGHT - 100
        
        self.pontos = 0
        self.score = 0

        self.pos = 0
        
    def update(self):
        self.rect.x += self.pos
        self.pontos += self.score
        
        if self.rect.right < ch.WIDTH/2 - 30:
            self.rect.right = ch.WIDTH/2 - 30
        if self.rect.left > ch.WIDTH/2 + 30:
            self.rect.left = ch.WIDTH/2 + 30
        self.score = 0
